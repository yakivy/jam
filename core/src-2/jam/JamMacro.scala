package jam

import jam.tree.annotated.brewable
import scala.reflect.macros.blackbox.Context

object JamMacro {
    def brewImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        val candidates = findCandidates(c)
        brew(c)(candidates, implicitly[c.WeakTypeTag[J]].tpe.dealias, "") { (tpe, prefix) =>
            c.abort(c.enclosingPosition, s"Unable to find instance for $prefix($tpe)")
        }
    }

    def brewTreeImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        val candidates = findCandidates(c)
        brew(c)(candidates, implicitly[c.WeakTypeTag[J]].tpe.dealias, "") { (tpe, prefix) =>
            def rec(tpe: c.Type, typePrefix: String): c.Tree = brew(c)(candidates, tpe, typePrefix)(rec(_, _)).tree
            rec(tpe, prefix)
        }
    }

    def brewAnnotatedTreeImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        val candidates = findCandidates(c)
        brew(c)(candidates, implicitly[c.WeakTypeTag[J]].tpe.dealias, "") { (tpe, prefix) =>
            if (tpe.typeSymbol.annotations.exists(_.tree.tpe =:= c.typeOf[brewable])) {
                def rec(tpe: c.Type, prefix: String): c.Tree = brew(c)(candidates, tpe, prefix)(rec(_, _)).tree
                rec(tpe, prefix)
            } else c.abort(c.enclosingPosition, s"Unable to find instance for $prefix($tpe)")
        }
    }

    private def findCandidates(c: Context): List[(c.universe.TermSymbol, c.universe.Type)] = {
        import c.universe._
        val defs: Map[TermName, Type] = c.enclosingImpl.collect {
            case DefDef(_, n, _, Nil, tpt, rhs) => (n, tpt, rhs)
            case ValDef(_, n, tpt, rhs) => (n, tpt, rhs)
        }.flatMap(m => Option(m._2.tpe).orElse(
            Option(m._3).filter(_ != EmptyTree).map(c.typecheck(_, silent = true, withMacrosDisabled = true).tpe)
        ).map(m._1 -> _)).toMap
        c.typecheck(q"this").tpe.members
            .filter(_.isTerm).map(_.asTerm).map(s => s.getter.orElse(s).asTerm).toList.distinct
            .map(m => m -> defs.getOrElse(m.name, m.typeSignature))
    }

    private def brew[J](
        c: Context)(
        candidates: List[(c.universe.TermSymbol, c.universe.Type)], tpe: c.Type, prefix: String)(
        find: (c.Type, String) => c.Tree
    ): c.Expr[J] = {
        import c.universe._
        val constructors = tpe.members
            .filter(m => m.isMethod && m.isPublic)
            .map(_.asMethod)
            .filter(m => m.isConstructor && m.returnType == tpe)
        if (constructors.isEmpty)
            c.abort(c.enclosingPosition, s"Unable to find public constructor for $prefix($tpe)")
        if (constructors.size > 1)
            c.abort(c.enclosingPosition, s"More than one primary constructor was found for $prefix($tpe)")
        val constructorArgs = constructors.head.paramLists.map(_.map(p =>
            if (p.isImplicit) {
                q"""def implicitlyWithMessage[A](implicit @_root_.scala.annotation.implicitNotFound(${
                    s"Unable to resolve implicit instance for $prefix($tpe).${p.name}"
                }) value: A): A = value
               implicitlyWithMessage[${p.typeSignature}]"""
            } else {
                val parameterCandidates = candidates.filter { m =>
                    m._2.finalResultType <:< p.typeSignature.finalResultType &&
                        (!m._1.isMethod || m._1.asMethod.paramLists.flatten.isEmpty)
                }
                if (parameterCandidates.size > 1)
                    c.abort(c.enclosingPosition, s"More than one injection candidate was found for $prefix($tpe).${p.name}")
                parameterCandidates.headOption.fold(
                    find(p.typeSignature, s"$prefix($tpe).${p.name}"))(
                    m => q"this.${m._1.name}"
                )
            }
        ))
        c.Expr(q"new $tpe(...$constructorArgs)")
    }
}
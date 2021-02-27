package jam

import jam.tree.annotated.brewable
import scala.reflect.macros.blackbox.Context

object JamMacro {
    def brewImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        brew(c)(implicitly[c.WeakTypeTag[J]].tpe.dealias, "") { (tpe, prefix) =>
            c.abort(c.enclosingPosition, s"Unable to find instance for $prefix`$tpe`")
        }
    }

    def brewTreeImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        brew(c)(implicitly[c.WeakTypeTag[J]].tpe.dealias, "") { (tpe, prefix) =>
            def rec(tpe: c.Type, typePrefix: String): c.Tree = brew(c)(tpe, typePrefix)(rec(_, _)).tree
            rec(tpe, prefix)
        }
    }

    def brewAnnotatedTreeImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        brew(c)(implicitly[c.WeakTypeTag[J]].tpe.dealias, "") { (tpe, prefix) =>
            if (tpe.typeSymbol.annotations.exists(_.tree.tpe =:= c.typeOf[brewable])) {
                def rec(tpe: c.Type, prefix: String): c.Tree = brew(c)(tpe, prefix)(rec(_, _)).tree
                rec(tpe, prefix)
            } else {
                c.abort(c.enclosingPosition, s"Unable to find instance for $prefix`$tpe`")
            }
        }
    }

    private def brew[J](
        c: Context)(
        tpe: c.Type, prefix: String)(
        onEmptyCandidate: (c.Type, String) => c.Tree
    ): c.Expr[J] = {
        import c.universe._
        val constructors = tpe.members
            .filter(m => m.isMethod && m.isPublic)
            .map(_.asMethod)
            .filter(m => m.isConstructor && m.returnType == tpe)
        if (constructors.isEmpty)
            c.abort(c.enclosingPosition, s"Unable to find public constructor for $prefix`$tpe`")
        if (constructors.size > 1)
            c.abort(c.enclosingPosition, s"More than one primary constructor was found for $prefix`$tpe`")
        val candidates: Iterable[c.universe.MethodSymbol] = c.typecheck(q"this").tpe.members
            .filter(_.isMethod).map(_.asMethod)
            .filter(_.paramLists.flatten.isEmpty)
        val constructorArgs = constructors.head.paramLists.map(_.map(p =>
            if (p.isImplicit) q"implicitly[${p.typeSignature}]"
            else {
                val parameterCandidates = candidates
                    .filter(c => c.returnType == p.typeSignature && c.paramLists.flatten.isEmpty)
                if (parameterCandidates.size > 1)
                    c.abort(c.enclosingPosition, s"More than one injection candidate was found for $prefix`$tpe`.`${p.name}`")
                parameterCandidates.headOption.fold(
                    onEmptyCandidate(p.typeSignature, s"$prefix`$tpe`."))(
                    m => q"this.${m.name}"
                )
            }
        ))
        c.Expr(q"new $tpe(...$constructorArgs)")
    }
}
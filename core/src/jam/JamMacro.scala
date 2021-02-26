package jam

import jam.tree.annotated.brewable
import scala.reflect.macros.blackbox.Context

object JamMacro {
    def brewImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        brew(c)(implicitly[c.WeakTypeTag[J]].tpe.dealias) { t =>
            c.abort(c.enclosingPosition, s"Unable to find instance for $t")
        }
    }

    def brewTreeImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        brew(c)(implicitly[c.WeakTypeTag[J]].tpe.dealias) {
            def rec(t: c.Type): c.Tree = brew(c)(t)(rec(_)).tree; rec(_)
        }
    }

    def brewAnnotatedTreeImpl[J: c.WeakTypeTag](c: Context): c.Expr[J] = {
        brew(c)(implicitly[c.WeakTypeTag[J]].tpe.dealias) { t =>
            if (t.typeSymbol.annotations.exists(_.tree.tpe =:= c.typeOf[brewable])) {
                def rec(t: c.Type): c.Tree = brew(c)(t)(rec(_)).tree; rec(t)
            } else {
                c.abort(c.enclosingPosition, s"Unable to find instance for $t")
            }
        }
    }

    private def brew[J](
        c: Context)(
        `type`: c.Type)(
        onEmptyCandidate: c.Type => c.Tree
    ): c.Expr[J] = {
        import c.universe._
        val constructors = `type`.members
            .filter(m => m.isMethod && m.isPublic)
            .map(_.asMethod)
            .filter(m => m.isConstructor && m.returnType == `type`)
        if (constructors.isEmpty)
            c.abort(c.enclosingPosition, s"Unable to find public constructor for ${`type`}")
        if (constructors.size > 1)
            c.abort(c.enclosingPosition, s"More than one primary constructor was found for ${`type`}")
        val candidates: Iterable[c.universe.MethodSymbol] = c.typecheck(q"this").tpe.members
            .filter(_.isMethod).map(_.asMethod)
            .filter(_.paramLists.flatten.isEmpty)
        val constructorArgs = constructors.head.paramLists.map(_.map(p =>
            if (p.isImplicit) q"implicitly[${p.typeSignature}]"
            else {
                val parameterCandidates = candidates
                    .filter(c => c.returnType == p.typeSignature && c.paramLists.flatten.isEmpty)
                if (parameterCandidates.size > 1)
                    c.abort(c.enclosingPosition, s"More than one injection candidate was found for ${`type`}.${p.name}")
                parameterCandidates.headOption.fold(onEmptyCandidate(p.typeSignature))(m => q"this.${m.name}")
            }
        ))
        c.Expr(q"new ${`type`}(...$constructorArgs)")
    }
}
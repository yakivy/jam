package jam

import scala.reflect.macros.blackbox

object JamMacro {
    def brewImpl[J: c.WeakTypeTag](c: blackbox.Context): c.Expr[J] = {
        c.Expr(brewRec(c)(implicitly[c.WeakTypeTag[J]].tpe.dealias))
    }

    private def brewRec[J](c: blackbox.Context)(`type`: c.Type): c.Tree = {
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
            .filter(_.fullName != c.internal.enclosingOwner.fullName)
            .filter(_.paramLists.flatten.isEmpty)
        val constructorArgs = constructors.head.paramLists.map(_.map(p =>
            if (p.isImplicit) q"implicitly[${p.typeSignature}]"
            else {
                val parameterCandidates = candidates.filter(_.returnType == p.typeSignature)
                if (parameterCandidates.size > 1)
                    c.abort(c.enclosingPosition, s"More than one injection candidate was found for ${`type`}.${p.name}")
                parameterCandidates.headOption.fold(brewRec(c)(p.typeSignature))(m => q"this.${m.name}")
            }
        ))
        q"new ${`type`}(...$constructorArgs)"
    }
}
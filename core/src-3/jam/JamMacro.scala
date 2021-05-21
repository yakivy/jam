package jam

import jam.tree.annotated.brewable
import scala.quoted.*

object JamMacro {
    def brewImpl[J](using q: Quotes)(using Type[J]): Expr[J] = {
        import q.reflect.*
        brew(findSelf, TypeTree.of[J], ""){ (ttr, prefix) =>
            report.throwError(s"Unable to find instance for $prefix(${ttr.show})")
        }.asExprOf
    }
    def brewTreeImpl[J](using q: Quotes)(using Type[J]): Expr[J] = {
        import q.reflect.*
        val self = findSelf
        brew(self, TypeTree.of[J], "") { (ttr, prefix) =>
            def rec(ttr: TypeTree, typePrefix: String): Expr[?] = brew(self, ttr, typePrefix)(rec(_, _))
            rec(ttr, prefix)
        }.asExprOf
    }
    def brewAnnotatedTreeImpl[J](using q: Quotes)(using Type[J]): Expr[J] = {
        import q.reflect.*
        val self = findSelf
        brew(self, TypeTree.of[J], "") { (ttr, prefix) =>
            if (ttr.tpe.typeSymbol.annotations.exists(_.tpe =:= TypeRepr.of[brewable])) {
                def rec(ttr: TypeTree, prefix: String): Expr[?] = brew(self, ttr, prefix)(rec(_, _))
                rec(ttr, prefix)
            } else report.throwError(s"Unable to find instance for $prefix(${ttr.show})")
        }.asExprOf
    }

    private def findSelf(using q: Quotes): q.reflect.This = {
        import q.reflect.*
        def rec(s: Symbol): Option[Symbol] = s.maybeOwner match {
            case o if o.isNoSymbol => None
            case o if o.isClassDef || o.isPackageDef => Option(o)
            case o => rec(o)
        }
        rec(Symbol.spliceOwner).map(This.apply)
            .getOrElse(report.throwError(s"Unable to access 'this'"))
    }

    private def brew(
        using q: Quotes)(
        self: q.reflect.This,
        ttr: q.reflect.TypeTree,
        prefix: String)(
        find: (q.reflect.TypeTree, String) => Expr[?]
    ): Expr[?] = {
        import q.reflect.*
        val constructors = ttr.tpe.typeSymbol.declarations.filter(m => m.isClassConstructor).map(_.tree).collect {
            case m: DefDef if m.returnTpt.tpe =:= ttr.tpe => m
        }
        if (constructors.isEmpty) report.throwError(s"Unable to find public constructor for $prefix(${ttr.show})")
        if (constructors.size > 1)
            report.throwError(s"More than one primary constructor was found for $prefix(${ttr.show})")
        val candidates = (self.tpe.typeSymbol.memberMethods ::: self.tpe.typeSymbol.memberFields).map(_.tree).collect {
            case m: ValDef => (m.symbol, Nil, m.rhs.map(_.tpe).getOrElse(m.tpt.tpe))
            case m: DefDef if m.termParamss.flatMap(_.params).isEmpty =>
                (m.symbol, m.termParamss.map(_ => Nil), m.rhs.map(_.tpe).getOrElse(m.returnTpt.tpe))
        }
        val constructorArgs = constructors.head.termParamss.map(tp => tp.params.map(p =>
            if (tp.isImplicit) p.tpt.tpe.asType match {
                case '[tpe] => (Expr.summon[tpe] match {
                    case Some(arg) => arg
                    case _ => report.throwError(
                        s"Unable to resolve implicit instance for $prefix(${ttr.show}).${p.name}"
                    )
                }).asTerm
            } else {
                val parameterCandidates = candidates.filter(_._3 =:= p.tpt.tpe)
                if (parameterCandidates.size > 1) report.throwError(
                    s"More than one injection candidate was found for $prefix(${ttr.show}).${p.name}"
                )
                parameterCandidates.headOption.fold(
                    find(p.tpt, s"$prefix(${ttr.show}).${p.name}").asTerm)(
                    m => m._2.foldLeft[Term](Select(self, m._1))(Apply(_, _))
                )
            }
        ))
        constructorArgs.map(_.map(_.asExpr.asTerm)).foldLeft[Term](Select(
            New(ttr), ttr.tpe.typeSymbol.primaryConstructor
        ))(Apply(_, _)).asExpr
    }
}
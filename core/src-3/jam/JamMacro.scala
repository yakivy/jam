package jam

import jam.tree.annotated.brewable
import scala.quoted.*

object JamMacro {
    def brewImpl[J](using Type[J], Quotes): Expr[J] = new JamMacro[J].brewImpl
    def brewTreeImpl[J](using Type[J], Quotes): Expr[J] = new JamMacro[J].brewTreeImpl
    def brewAnnotatedTreeImpl[J](using Type[J], Quotes): Expr[J] = new JamMacro[J].brewAnnotatedTreeImpl
}

class JamMacro[J](using Type[J], Quotes) {
    import quotes.reflect.*

    private def self: Option[This] = {
        def rec(s: Symbol): Option[Symbol] = s.maybeOwner match {
            case o if o.isNoSymbol => None
            case o if o.isClassDef || o.isPackageDef => Option(o)
            case o => rec(o)
        }
        rec(Symbol.spliceOwner).map(This.apply)
    }

    def brewImpl: Expr[J] =
        self.flatMap(brew(_, TypeTree.of[J], ""){ (ttr, prefix) => None }).getOrElse('{???}).asExprOf

    def brewTreeImpl: Expr[J] =
        self.flatMap(sr => brew(sr, TypeTree.of[J], "") { (ttr, prefix) =>
            def rec(ttr: TypeTree, typePrefix: String): Option[Expr[?]] = brew(sr, ttr, typePrefix)(rec(_, _))
            rec(ttr, prefix)
        }).getOrElse('{???}).asExprOf

    def brewAnnotatedTreeImpl: Expr[J] = {
        self.flatMap(sr => brew(sr, TypeTree.of[J], "") { (ttr, prefix) =>
            if (ttr.tpe.typeSymbol.annotations.exists(_.tpe =:= TypeRepr.of[brewable])) {
                def rec(ttr: TypeTree, prefix: String): Option[Expr[?]] = brew(sr, ttr, prefix)(rec(_, _))
                rec(ttr, prefix)
            } else {
                report.error(s"Unable to find instance for $prefix(${ttr.show})", Position.ofMacroExpansion)
                None
            }
        }).getOrElse('{???}).asExprOf
    }

    private def brew(
        self: This,
        ttr: TypeTree,
        prefix: String)(
        find: (TypeTree, String) => Option[Expr[?]]
    ): Option[Expr[?]] = {
        val constructors = ttr.tpe.typeSymbol.declarations.filter(m => m.isClassConstructor).map(_.tree).collect {
            case m: DefDef if m.returnTpt.tpe =:= ttr.tpe => m
        }
        if (constructors.isEmpty) {
            report.error(s"Unable to find public constructor for $prefix(${ttr.show})", Position.ofMacroExpansion)
            return None
        }
        if (constructors.size > 1) {
            report.error(
                s"More than one primary constructor was found for $prefix(${ttr.show})", Position.ofMacroExpansion
            )
            return None
        }
        val candidates = (self.tpe.typeSymbol.memberMethods ::: self.tpe.typeSymbol.memberFields).map(_.tree).collect {
            case m: ValDef => (m.symbol, Nil, m.tpt)
            case m: DefDef if m.termParamss.flatMap(_.params).isEmpty =>
                (m.symbol, m.termParamss.map(_ => Nil), m.returnTpt)
        }
        val constructorArgs = constructors.head.termParamss.map(tp => tp.params.map(p =>
            if (tp.isImplicit) p.tpt.tpe.asType match {
                case '[tpe] => (Expr.summon[tpe] match {
                    case Some(arg) => arg
                    case _ =>
                        report.error(
                            s"Unable to resolve implicit instance for $prefix(${ttr.show}).${p.name}",
                            Position.ofMacroExpansion
                        )
                        return None
                }).asTerm
            } else {
                val parameterCandidates = candidates.filter(_._3.tpe =:= p.tpt.tpe)
                if (parameterCandidates.size > 1) {
                    report.error(
                        s"More than one injection candidate was found for $prefix(${ttr.show}).${p.name}",
                        Position.ofMacroExpansion
                    )
                    return None
                }
                parameterCandidates.headOption.fold(
                    find(p.tpt, s"$prefix(${ttr.show}).${p.name}").map(_.asTerm))(
                    m => Option(m._2.foldLeft[Term](Select(self, m._1))(Apply(_, _)))
                ).getOrElse {
                    return None
                }
            }
        ))
        Option(constructorArgs.map(_.map(_.asExpr.asTerm)).foldLeft[Term](Select(
            New(ttr), ttr.tpe.typeSymbol.primaryConstructor
        ))(Apply(_, _)).asExpr)
    }
}
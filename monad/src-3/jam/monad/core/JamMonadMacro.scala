package jam.monad.core

import scala.quoted.*

object JamMonadMacro {
    def positionKeyGenImpl[A: Type](using q: Quotes): Expr[RevalKeyGen[A]] = {
        import q.reflect.*
        val tpe = TypeRepr.of[A]
        if(tpe.typeSymbol.isTypeParam) report.errorAndAbort(
            "Reval.later cannot resolve concrete result type to delay initialization. " +
                "Please add implicit RevalKeyGen argument if your type is generic or use Reval.always"
        )
        val pos = Position.ofMacroExpansion
        val key = s"path:${pos.sourceFile.path};like:${pos.startLine}:${pos.startColumn};tpe:${tpe.show}"
        '{ RevalKeyGen[A](${ Expr(key) }) }
    }
}

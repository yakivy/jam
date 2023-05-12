package jam.monad.core

import scala.reflect.macros.blackbox.Context

private[jam] class JamMonadMacro(val c: Context) {
    import c.universe._

    def positionKeyGenImpl[A: c.WeakTypeTag]: c.Tree = {
        val tpe = weakTypeOf[A]
        if (tpe.typeSymbol.isParameter) c.abort(
            c.enclosingPosition,
            "Reval.later cannot resolve concrete result type to delay initialization. " +
                "Please add implicit RevalKeyGen argument if your type is generic or use Reval.always"
        )
        val pos = c.macroApplication.pos
        val key = s"path:${pos.source.file.path};like:${pos.line}:${pos.column};tpe:$tpe"
        q"""_root_.jam.monad.core.RevalKeyGen($key)"""
    }
}

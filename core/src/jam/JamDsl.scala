package jam

import jam.core.JamDslBinCompat

trait JamDsl extends JamDslBinCompat {
    final class JamConfig(
        val brewRecRegex: String,
    )
}

package com.daro.kmmtest.core

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual class KMMLogger {

    init {
        setup()
    }

    actual fun setup() {
        Napier.base(DebugAntilog())
    }
}

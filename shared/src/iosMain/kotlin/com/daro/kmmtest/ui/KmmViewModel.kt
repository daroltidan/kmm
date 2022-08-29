package com.daro.kmmtest.ui

import io.github.aakira.napier.Napier
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class KmmViewModel {
    actual val scope = MainScope()

    protected actual open fun onCleared() {
        Napier.i("view model cleared")
        scope.cancel()
    }

    init {
        Napier.i("view model started from ios")
    }
}

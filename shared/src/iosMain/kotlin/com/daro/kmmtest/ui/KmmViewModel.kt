package com.daro.kmmtest.ui

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class KmmViewModel {
    actual val scope = MainScope()

    protected actual open fun onCleared() {
    }

    fun clear() {
        onCleared()
        scope.cancel()
    }
}

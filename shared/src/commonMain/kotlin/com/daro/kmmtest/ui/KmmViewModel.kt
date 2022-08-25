package com.daro.kmmtest.ui

import kotlinx.coroutines.CoroutineScope

expect abstract class KmmViewModel() {
    val scope: CoroutineScope
    protected open fun onCleared()
}

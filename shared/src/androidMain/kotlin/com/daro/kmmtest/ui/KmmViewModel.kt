package com.daro.kmmtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual abstract class KmmViewModel actual constructor() : ViewModel() {
    actual val scope: CoroutineScope = viewModelScope

    actual override fun onCleared() {
        super.onCleared()
    }
}

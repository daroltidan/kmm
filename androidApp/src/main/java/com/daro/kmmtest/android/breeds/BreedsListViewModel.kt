package com.daro.kmmtest.android.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daro.kmmtest.domain.GetAllBreeds
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

class BreedsListViewModel(
    private val getAllBreeds: GetAllBreeds
) : ViewModel() {

    val breedsList = flow {
        emit(getAllBreeds.getAllBreeds())
    }.shareIn(
        scope = viewModelScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )
}

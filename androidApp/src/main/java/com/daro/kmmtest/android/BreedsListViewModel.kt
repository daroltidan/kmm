package com.daro.kmmtest.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daro.kmmtest.domain.BreedDTO
import com.daro.kmmtest.domain.GetAllBreeds
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BreedsListViewModel(
    private val getAllBreeds: GetAllBreeds
) : ViewModel() {

    private val _breedsList = MutableStateFlow<List<BreedDTO>>(emptyList())
    val breedsList = _breedsList.asStateFlow()

    init {
        getBreeds()
    }

    private fun getBreeds() {
        viewModelScope.launch(
            context = CoroutineExceptionHandler { _, throwable ->
                Log.e(
                    BreedsListViewModel::class.java.simpleName,
                    throwable.message.toString()
                )
            }
        ) {
            _breedsList.value = getAllBreeds()
        }
    }
}

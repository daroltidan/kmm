package com.daro.kmmtest.ui

import com.daro.kmmtest.domain.BreedDTO
import com.daro.kmmtest.domain.GetAllBreeds
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BreedsListViewModel(
    private val getAllBreeds: GetAllBreeds
) : KmmViewModel() {

    private val _breedsList: MutableStateFlow<List<BreedDTO>> =
        MutableStateFlow(emptyList())
    val breedsList: StateFlow<List<BreedDTO>> = _breedsList.asStateFlow()

    init {
        getBreeds()
    }

    private fun getBreeds() {
        scope.launch(
            context = CoroutineExceptionHandler { _, throwable ->
                Napier.e(throwable = throwable, message = "failed to retrieve breeds")
            }
        ) {
            _breedsList.value = getAllBreeds()
        }
    }
}

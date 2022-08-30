package com.daro.kmmtest.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class GetAllBreeds(
    private val repository: DogsRepository
) {
    private val scope: CoroutineScope = MainScope()

    suspend fun getAllBreeds(): List<BreedDTO> = repository.getAllBreeds()

    @Suppress("UNUSED") //used in ios
    fun getAllBreeds(success: (List<BreedDTO>) -> Unit) {
        scope.launch {
            success(getAllBreeds())
        }
    }
}

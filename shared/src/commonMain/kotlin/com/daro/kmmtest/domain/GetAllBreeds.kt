package com.daro.kmmtest.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GetAllBreeds(
    private val repository: DogsRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend fun getAllBreeds(): List<BreedDTO> = repository.getAllBreeds()

    @Suppress("UNUSED") //used in ios
    fun getAllBreeds(success: (List<BreedDTO>) -> Unit) {
        CoroutineScope(coroutineDispatcher).launch { success(getAllBreeds()) }
    }
}

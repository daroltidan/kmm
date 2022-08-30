package com.daro.kmmtest.domain

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetAllBreeds(
    private val repository: DogsRepository
) {

    suspend operator fun invoke(): List<BreedDTO> = repository.getAllBreeds()

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllBreedsIOS(success: (List<BreedDTO>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            success(invoke())
        }
    }
}

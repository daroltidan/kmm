package com.daro.kmmtest.domain

class GetAllBreeds(
    private val repository: DogsRepository
) {

    suspend operator fun invoke() = repository.getAllBreeds()
}

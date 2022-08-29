package com.daro.kmmtest.domain

interface DogsRepository {

    suspend fun getAllBreeds(forceUpdate: Boolean = false): List<BreedDTO>
}

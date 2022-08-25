package com.daro.kmmtest.data.repos

import com.daro.kmmtest.data.responses.BreedsListResponseItem
import com.daro.kmmtest.data.services.DogsService
import com.daro.kmmtest.domain.DogsRepository

class DogsRepositoryImpl(private val dogsService: DogsService) : DogsRepository {

    override suspend fun getAllBreeds(): List<BreedsListResponseItem> {
        return dogsService.getDogBreedsList()
    }
}

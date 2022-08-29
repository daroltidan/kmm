package com.daro.kmmtest.data.sources

import com.daro.kmmtest.Breed
import com.daro.kmmtest.domain.BreedDTO

interface DogsDataSource {
    suspend fun getAllBreeds(): List<BreedDTO>

    suspend fun insertBreeds(breeds: List<Breed>)
}

package com.daro.kmmtest.data.services

import com.daro.kmmtest.data.responses.BreedsListResponseItem

interface DogsService {
    suspend fun getDogBreedsList(): List<BreedsListResponseItem>
}

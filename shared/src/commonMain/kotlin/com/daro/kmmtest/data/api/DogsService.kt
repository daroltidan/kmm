package com.daro.kmmtest.data.api

import com.daro.kmmtest.data.responses.BreedsListResponseItem

interface DogsService {
    suspend fun getDogBreedsList(): List<BreedsListResponseItem>
}

package com.daro.kmmtest.data.remote.api

import com.daro.kmmtest.data.remote.responses.BreedsListResponseItem

interface DogsService {
    suspend fun getDogBreedsList(): List<BreedsListResponseItem>
}

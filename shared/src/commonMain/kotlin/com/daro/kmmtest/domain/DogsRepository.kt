package com.daro.kmmtest.domain

import com.daro.kmmtest.data.responses.BreedsListResponseItem

interface DogsRepository {

    suspend fun getAllBreeds(): List<BreedsListResponseItem>
}

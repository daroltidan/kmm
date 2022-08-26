package com.daro.kmmtest.data.sources

import com.daro.kmmtest.data.responses.BreedsListResponseItem

interface DogsDataSource {
    suspend fun getAllBreeds(): List<BreedsListResponseItem>
}

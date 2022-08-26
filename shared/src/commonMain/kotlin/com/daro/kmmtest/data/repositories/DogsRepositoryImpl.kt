package com.daro.kmmtest.data.repositories

import com.daro.kmmtest.data.responses.BreedsListResponseItem
import com.daro.kmmtest.data.sources.DogsDataSource
import com.daro.kmmtest.domain.DogsRepository

class DogsRepositoryImpl(private val dataSource: DogsDataSource) : DogsRepository {

    override suspend fun getAllBreeds(): List<BreedsListResponseItem> {
        return dataSource.getAllBreeds()
    }
}

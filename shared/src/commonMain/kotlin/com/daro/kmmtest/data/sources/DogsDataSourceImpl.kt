package com.daro.kmmtest.data.sources

import com.daro.kmmtest.data.api.DogsService
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DogsDataSourceImpl(
    private val dogsService: DogsService,
    private val coroutineDispatcher: CoroutineDispatcher
) : DogsDataSource {

    override suspend fun getAllBreeds() = withContext(coroutineDispatcher) {
        dogsService.getDogBreedsList()
    }
}

package com.daro.kmmtest.data.remote

import com.daro.kmmtest.Breed
import com.daro.kmmtest.data.remote.api.DogsService
import com.daro.kmmtest.data.remote.responses.mappers.RemoteBreedMapper
import com.daro.kmmtest.data.sources.DogsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteDogsDataSourceImpl(
    private val dogsService: DogsService,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val breedMapper: RemoteBreedMapper
) : DogsDataSource {

    override suspend fun getAllBreeds() = withContext(coroutineDispatcher) {
        val response = dogsService.getDogBreedsList()
        return@withContext response.map(breedMapper::invoke)
    }

    override suspend fun insertBreeds(breeds: List<Breed>) {
        //no op
    }
}

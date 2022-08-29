package com.daro.kmmtest.data.repositories

import com.daro.kmmtest.Breed
import com.daro.kmmtest.data.sources.DogsDataSource
import com.daro.kmmtest.domain.BreedDTO
import com.daro.kmmtest.domain.DogsRepository

class DogsRepositoryImpl(
    private val remoteDataSource: DogsDataSource,
    private val localDataSource: DogsDataSource,
) : DogsRepository {

    override suspend fun getAllBreeds(forceUpdate: Boolean): List<BreedDTO> {
        val localSource = localDataSource.getAllBreeds()
        if (forceUpdate || localSource.isEmpty()) {
            return getRemoteSaveAndShowLocalData()
        }
        return localSource
    }

    private suspend fun getRemoteSaveAndShowLocalData(): List<BreedDTO> {
        val remoteData = remoteDataSource.getAllBreeds()
        val localData = remoteData.map { Breed(id = it.id, name = it.name, image = it.image) }
        localDataSource.insertBreeds(localData)
        return localDataSource.getAllBreeds()
    }
}

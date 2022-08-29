package com.daro.kmmtest.data.local

import com.daro.kmmtest.Breed
import com.daro.kmmtest.data.local.mappers.LocalBreedMapper
import com.daro.kmmtest.data.sources.DogsDataSource
import com.daro.kmmtest.db.KmmDatabase
import com.daro.kmmtest.domain.BreedDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalDogsDataSourceImpl(
    private val kmmDatabase: KmmDatabase,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val breedMapper: LocalBreedMapper
) : DogsDataSource {

    override suspend fun getAllBreeds(): List<BreedDTO> = withContext(coroutineDispatcher) {
        val query = kmmDatabase.breedQueries.selectAll().executeAsList()
        return@withContext query.map(breedMapper::invoke)
    }

    override suspend fun insertBreeds(breeds: List<Breed>): Unit =
        withContext(coroutineDispatcher) {
            breeds.onEach {
                kmmDatabase.breedQueries.insertBreed(it.id, it.name, it.image)
            }
        }
}

package com.daro.kmmtest

import com.daro.kmmtest.data.local.LocalDogsDataSourceImpl
import com.daro.kmmtest.data.local.mappers.LocalBreedMapper
import com.daro.kmmtest.data.remote.RemoteDogsDataSourceImpl
import com.daro.kmmtest.data.remote.api.DogsService
import com.daro.kmmtest.data.remote.responses.BreedsListResponseItem
import com.daro.kmmtest.data.remote.responses.Height
import com.daro.kmmtest.data.remote.responses.Image
import com.daro.kmmtest.data.remote.responses.Weight
import com.daro.kmmtest.data.remote.responses.mappers.RemoteBreedMapper
import com.daro.kmmtest.data.repositories.DogsRepositoryImpl
import com.daro.kmmtest.data.sources.DogsDataSource
import com.daro.kmmtest.db.KmmDatabase
import com.daro.kmmtest.domain.BreedDTO
import com.daro.kmmtest.domain.DogsRepository
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

expect fun getDatabase(): SqlDriver

@ExperimentalCoroutinesApi
class TestBreedsList {

    private lateinit var remoteDogsSource: DogsDataSource
    private lateinit var dogsService: DogsService
    private lateinit var mapper: RemoteBreedMapper
    private lateinit var localDogsDataSource: DogsDataSource
    private lateinit var localBreedMapper: LocalBreedMapper
    private lateinit var database: KmmDatabase
    private lateinit var repository: DogsRepository

    private val dispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(dispatcher)
        dogsService = FakeDogsService()
        mapper = RemoteBreedMapper()
        localBreedMapper = LocalBreedMapper()
        database = KmmDatabase(getDatabase())

        remoteDogsSource = RemoteDogsDataSourceImpl(
            dogsService = dogsService,
            breedMapper = mapper,
            coroutineDispatcher = dispatcher
        )
        localDogsDataSource = LocalDogsDataSourceImpl(
            breedMapper = localBreedMapper,
            kmmDatabase = database,
            coroutineDispatcher = dispatcher
        )

        repository = DogsRepositoryImpl(
            localDataSource = localDogsDataSource,
            remoteDataSource = remoteDogsSource
        )
    }

    @Test
    fun `get remote data and map it`() = runTest {
        val mappedResponse = remoteDogsSource.getAllBreeds()
        assertEquals(
            mappedResponse,
            listOf(BreedDTO(id = 1, name = "name", image = ""))
        )
    }

    @Test
    fun `get local data and map it`() = runTest {
        database.breedQueries.insertBreed(id = null, name = "name", image = "image")

        val dataFromDb = database.breedQueries.selectAll().executeAsList()
        val allBreeds = localDogsDataSource.getAllBreeds()

        assertEquals(allBreeds, dataFromDb.map { localBreedMapper(it) })
    }

    @Test
    fun `get cachedData via repository`() = runTest {
        database.breedQueries.insertBreed(id = null, name = "name", image = "image")

        assertEquals(
            repository.getAllBreeds(forceUpdate = false),
            database.breedQueries.selectAll().executeAsList().map { localBreedMapper(it) })
    }

    @Test
    fun `get cachedData and network data via repository with force update`() = runTest {
        assertEquals(
            repository.getAllBreeds(forceUpdate = true),
            localDogsDataSource.getAllBreeds()
        )
    }

    @Test
    fun `get cachedData and network data via repository with empty data`() = runTest {
        assertEquals(
            repository.getAllBreeds(forceUpdate = false),
            localDogsDataSource.getAllBreeds()
        )
    }


}

internal class FakeDogsService : DogsService {
    override suspend fun getDogBreedsList() = listOf(
        BreedsListResponseItem(
            breedGroup = "breedGroup",
            image = Image(height = 0, width = 0, id = "", url = ""),
            name = "name",
            height = Height(imperial = "", metric = ""),
            weight = Weight(imperial = "", metric = ""),
            lifeSpan = "",
            id = 1,
            bredFor = "bredFor",
            origin = "origin",
            referenceImageId = "referenceImageId",
            temperament = "temperament"
        )
    )

}

package com.daro.kmmtest

import com.daro.kmmtest.data.remote.RemoteDogsDataSourceImpl
import com.daro.kmmtest.data.remote.api.DogsService
import com.daro.kmmtest.data.remote.responses.BreedsListResponseItem
import com.daro.kmmtest.data.remote.responses.Height
import com.daro.kmmtest.data.remote.responses.Image
import com.daro.kmmtest.data.remote.responses.Weight
import com.daro.kmmtest.data.remote.responses.mappers.RemoteBreedMapper
import com.daro.kmmtest.data.sources.DogsDataSource
import com.daro.kmmtest.domain.BreedDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


@ExperimentalCoroutinesApi
class TestBreedsList {

    private lateinit var remoteDogsSource: DogsDataSource
    private lateinit var dogsService: DogsService
    private lateinit var mapper: RemoteBreedMapper
    private val dispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(dispatcher)
        dogsService = FakeDogsService()
        mapper = RemoteBreedMapper()
        remoteDogsSource = RemoteDogsDataSourceImpl(
            dogsService = dogsService,
            breedMapper = mapper,
            coroutineDispatcher = dispatcher
        )
    }

    @Test
    fun `get data and map it`() = runTest {
        val mappedResponse = remoteDogsSource.getAllBreeds()
        assertEquals(
            mappedResponse,
            listOf(BreedDTO(id = 1, name = "name", image = ""))
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

package com.daro.kmmtest.data.remote.api

import com.daro.kmmtest.data.remote.HttpRoutes
import com.daro.kmmtest.data.remote.responses.BreedsListResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class DogsServiceImpl(private val client: HttpClient) : DogsService {

    override suspend fun getDogBreedsList(): List<BreedsListResponseItem> =
        client.get(HttpRoutes.breedsPath).body()
}

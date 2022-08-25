package com.daro.kmmtest.data.services

import com.daro.kmmtest.data.HttpRoutes
import com.daro.kmmtest.data.responses.BreedsListResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class DogsServiceImpl(
    private val client: HttpClient
) : DogsService {

    override suspend fun getDogBreedsList(): List<BreedsListResponseItem> {
        return client.get(HttpRoutes.breedsPath).body()
    }
}

package com.daro.kmmtest.data.api

import com.daro.kmmtest.data.responses.BreedsListResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class DogsServiceImpl(private val client: HttpClient) : DogsService {

    override suspend fun getDogBreedsList(): List<BreedsListResponseItem> =
        client.get("https://api.thedogapi.com/v1/breeds").body()
}

package com.daro.kmmtest.di

import com.daro.kmmtest.core.KMMLogger
import com.daro.kmmtest.data.API_KEY
import com.daro.kmmtest.data.HttpRoutes
import com.daro.kmmtest.data.repos.DogsRepositoryImpl
import com.daro.kmmtest.data.services.DogsService
import com.daro.kmmtest.data.services.DogsServiceImpl
import com.daro.kmmtest.domain.DogsRepository
import com.daro.kmmtest.domain.GetAllBreeds
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf<HttpClient> {
        return@singleOf HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(tag = "networking", message = message)
                    }
                }
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HttpRoutes.baseUrl
                    headers {
                        append(
                            "x-api-key",
                            API_KEY
                        )
                    }
                    contentType(ContentType.Application.Json)
                }
            }

            install(HttpTimeout) {
                val timeout = 30000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
        }.also { KMMLogger() }
    }

    single<DogsService> { DogsServiceImpl(client = get()) }
    single<DogsRepository> { DogsRepositoryImpl(dogsService = get()) }
    factory { GetAllBreeds(repository = get()) }
}

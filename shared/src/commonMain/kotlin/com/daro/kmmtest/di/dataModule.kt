package com.daro.kmmtest.di

import com.daro.kmmtest.core.KMMLogger
import com.daro.kmmtest.data.local.LocalDogsDataSourceImpl
import com.daro.kmmtest.data.local.mappers.LocalBreedMapper
import com.daro.kmmtest.data.remote.RemoteDogsDataSourceImpl
import com.daro.kmmtest.data.remote.api.DogsService
import com.daro.kmmtest.data.remote.api.DogsServiceImpl
import com.daro.kmmtest.data.remote.responses.mappers.RemoteBreedMapper
import com.daro.kmmtest.data.repositories.DogsRepositoryImpl
import com.daro.kmmtest.data.sources.DogsDataSource
import com.daro.kmmtest.db.KmmDatabase
import com.daro.kmmtest.domain.DogsRepository
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val dataModule = module {
    singleOf(::provideHttpClient)
    factoryOf(::RemoteBreedMapper)
    factoryOf(::LocalBreedMapper)
    singleOf(::DogsServiceImpl) { bind<DogsService>() }
    single<DogsRepository> {
        DogsRepositoryImpl(
            remoteDataSource = get(named("remote")),
            localDataSource = get(named("local"))
        )
    }

    single<DogsDataSource>(named("local")) {
        LocalDogsDataSourceImpl(
            coroutineDispatcher = get(named(DISPATCHER_IO)),
            kmmDatabase = KmmDatabase(get()),
            breedMapper = get()
        )
    }
    single<DogsDataSource>(named("remote")) {
        RemoteDogsDataSourceImpl(
            dogsService = get(),
            coroutineDispatcher = get(named(DISPATCHER_IO)),
            breedMapper = get()
        )
    }
}

private fun provideHttpClient(httpClientEngine: HttpClientEngine) = HttpClient(httpClientEngine) {
    expectSuccess = true
    install(HttpCache)
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            },
            contentType = ContentType.Application.Json
        )

    }
    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                Napier.i { message }
            }
        }
    }

    defaultRequest {
        url {
            headers {
                append(
                    "x-api-key",
                    "live_ezSkCtxPvrrxndf1Bgo0D0nncA3y9aVtMFFeKVJROGpaPquPIBM7IHDujA0t12HE"
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
}.also { KMMLogger().setup() }

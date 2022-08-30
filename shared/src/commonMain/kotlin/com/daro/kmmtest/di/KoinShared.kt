package com.daro.kmmtest.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

const val DISPATCHER_IO = "DISPATCHER_IO"
const val DISPATCHER_MAIN = "DISPATCHER_MAIN"
const val DISPATCHER_UNCONFINED = "DISPATCHER_UNCONFINED"
const val DISPATCHER_DEFAULT = "DISPATCHER_DEFAULT"

const val DB_NAME = "breeds_database"
fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            dataModule,
            useCasesModule,
            platformModule
        )
    }

    return koinApplication
}

expect val platformModule: Module

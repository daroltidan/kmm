package com.daro.kmmtest.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            dataModule
        )
    }

    return koinApplication
}

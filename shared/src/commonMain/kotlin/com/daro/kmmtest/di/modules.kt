package com.daro.kmmtest.di

import com.daro.kmmtest.Greeting
import com.daro.kmmtest.Platform
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val platformModule = module {
    singleOf(::Platform)
}

val commonModule = module {
    singleOf(::Greeting)
}

fun appModule() = listOf(commonModule, platformModule)


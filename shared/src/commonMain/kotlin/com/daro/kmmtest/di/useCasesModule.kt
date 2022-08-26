package com.daro.kmmtest.di

import com.daro.kmmtest.domain.GetAllBreeds
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val useCasesModule = module {
    factoryOf(::GetAllBreeds)
}

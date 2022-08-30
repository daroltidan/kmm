package com.daro.kmmtest.di

import com.daro.kmmtest.domain.GetAllBreeds
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val useCasesModule = module {
    factory {
        GetAllBreeds(
            repository = get(),
            coroutineDispatcher = get(named(DISPATCHER_MAIN))
        )
    }
}

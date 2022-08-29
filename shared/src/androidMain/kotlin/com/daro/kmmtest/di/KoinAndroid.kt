package com.daro.kmmtest.di

import com.daro.kmmtest.db.KmmDatabase
import com.daro.kmmtest.ui.BreedsListViewModel
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.android.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<CoroutineDispatcher>(named(DISPATCHER_MAIN)) { Dispatchers.Main }
    single(named(DISPATCHER_IO)) { Dispatchers.IO }
    single(named(DISPATCHER_UNCONFINED)) { Dispatchers.Unconfined }
    single(named(DISPATCHER_DEFAULT)) { Dispatchers.Default }
    single { Android.create() }

    viewModel { BreedsListViewModel(getAllBreeds = get()) }

    single<SqlDriver> {
        AndroidSqliteDriver(
            KmmDatabase.Schema,
            get(),
            "breeds_database"
        )
    }
}

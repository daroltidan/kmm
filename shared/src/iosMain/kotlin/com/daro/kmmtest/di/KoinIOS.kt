package com.daro.kmmtest.di

import com.daro.kmmtest.db.KmmDatabase
import com.daro.kmmtest.domain.GetAllBreeds
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.engine.darwin.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named
import org.koin.dsl.module

@Suppress("UNUSED") //used in ios
fun initKoinIos(
    doOnStartup: () -> Unit
): KoinApplication = initKoin(
    module {
        single { doOnStartup }
    }
)

actual val platformModule = module {
    single<CoroutineDispatcher>(named(DISPATCHER_MAIN)) { Dispatchers.Main }
    single(named(DISPATCHER_IO)) { Dispatchers.Default }
    single(named(DISPATCHER_UNCONFINED)) { Dispatchers.Unconfined }
    single(named(DISPATCHER_DEFAULT)) { Dispatchers.Default }
    single { Darwin.create() }

    single<SqlDriver> { NativeSqliteDriver(KmmDatabase.Schema, DB_NAME) }

}

@Suppress("UNUSED") //used in ios client
object IOSInjectables : KoinComponent {
    fun getBreedsUseCase() = get<GetAllBreeds>()
}

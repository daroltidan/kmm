package com.daro.kmmtest.android

import android.app.Application
import com.daro.kmmtest.android.breeds.BreedsListViewModel
import com.daro.kmmtest.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.logger.Level
import org.koin.dsl.module

class KmmApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            appModule = module {
                viewModel {
                    BreedsListViewModel(get())
                }
            }
        ).apply {
            androidContext(this@KmmApplication)
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        }
    }
}

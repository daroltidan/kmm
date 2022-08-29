package com.daro.kmmtest.android

import android.app.Application
import com.daro.kmmtest.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.module

class KmmApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            appModule = module {
                // TODO: add android app specifics here
            }
        ).apply {
            androidContext(this@KmmApplication)
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        }
    }
}

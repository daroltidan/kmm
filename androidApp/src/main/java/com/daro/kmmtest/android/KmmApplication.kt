package com.daro.kmmtest.android

import android.app.Application
import com.daro.kmmtest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KmmApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KmmApplication)
            androidLogger()
            modules(appModule())
        }
    }
}

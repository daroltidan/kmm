package com.daro.kmmtest.android

import android.app.Application
import com.daro.kmmtest.di.initKoin
import com.daro.kmmtest.ui.BreedsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class KmmApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            appModule = module {
                viewModel {
                    BreedsListViewModel(getAllBreeds = get())
                }
            }
        ).apply {
            androidContext(this@KmmApplication)
        }
    }
}

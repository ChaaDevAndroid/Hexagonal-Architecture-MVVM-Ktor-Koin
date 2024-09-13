package com.chaaba.hexagonalarchitectureandmvvm

import android.app.Application
import com.chaaba.hexagonalarchitectureandmvvm.di.ModuleProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MealApp :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MealApp)
            //Load Modules
            modules(ModuleProvider.modules)
        }
    }
}
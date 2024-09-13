package com.chaaba.hexagonalarchitectureandmvvm.di

import android.content.ContentResolver
import com.chaaba.domain.DomainModule
import com.chaaba.hexagonalarchitectureandmvvm.ui.vm.MealViewModel
import com.chaaba.network.NetworkModule
import org.koin.core.module.Module
import org.koin.dsl.module

object ModuleProvider {
    val viewModelModule = module {
        single { MealViewModel(get()) }
    }

    val modules: List<Module>
        get() {
            return ArrayList<Module>().apply {
                addAll(NetworkModule.modules)
                addAll(DomainModule.modules)
                add(viewModelModule)
            }
        }

}
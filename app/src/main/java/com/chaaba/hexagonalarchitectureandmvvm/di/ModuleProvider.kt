package com.chaaba.hexagonalarchitectureandmvvm.di

import android.content.ContentResolver
import com.chaaba.domain.DomainModule
import com.chaaba.hexagonalarchitectureandmvvm.ui.vm.MealViewModel
import com.chaaba.network.NetworkModule
import com.chaaba.persistence.PersistenceModule
import org.koin.core.module.Module
import org.koin.dsl.module

object ModuleProvider {
    private val viewModelModule = module {
        single { MealViewModel(get()) }
    }

    val modules: List<Module>
        get() {
            return ArrayList<Module>().apply {
                addAll(NetworkModule.modules)
                addAll(DomainModule.modules)
                add(viewModelModule)
                addAll(PersistenceModule.modules)
            }
        }

}
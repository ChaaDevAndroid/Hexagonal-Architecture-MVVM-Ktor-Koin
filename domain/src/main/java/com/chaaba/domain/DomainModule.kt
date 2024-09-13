package com.chaaba.domain

import com.chaaba.domain.repo.MealRepoAdapter
import com.chaaba.library.repo.MealRepo
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    private val repositoryModule = module {
        single<MealRepo> { MealRepoAdapter(get()) }
    }

    val modules: List<Module> = listOf(repositoryModule)

}
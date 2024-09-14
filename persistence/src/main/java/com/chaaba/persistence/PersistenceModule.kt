package com.chaaba.persistence

import androidx.room.Room
import com.chaaba.domain.ports.network.MealNetworkPort
import com.chaaba.domain.ports.persistence.MealPersistencePort
import com.chaaba.persistence.adapter.MealPersistenceAdapter
import com.chaaba.persistence.db.MDataBase
import com.chaaba.persistence.db.MealDao
import org.koin.core.module.Module
import org.koin.dsl.module

object PersistenceModule {

    private val portModule = module {
        single<MealPersistencePort> { MealPersistenceAdapter(get()) }
    }

    private val dbModule = module {
        single<MDataBase> {
            Room.databaseBuilder(
                get(), MDataBase::class.java, "database"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }

    private val daoMealModule = module {
        single<MealDao> { get<MDataBase>().getMealsDao() }
    }

    val modules: List<Module> = listOf(
        daoMealModule, dbModule, portModule
    )

}
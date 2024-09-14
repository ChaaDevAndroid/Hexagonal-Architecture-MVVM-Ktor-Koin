package com.chaaba.persistence.adapter

import com.chaaba.domain.ports.persistence.*
import com.chaaba.library.Result
import com.chaaba.library.dto.Meal
import com.chaaba.persistence.db.MealDao
import com.chaaba.persistence.poko.MealEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MealPersistenceAdapter(private val mealDoa: MealDao) : MealPersistencePort {
    override suspend fun getMealsFromLocal(): Flow<Result<List<Meal>>> = flow {
        Result.Success(mealDoa.getMeals())
    }

    override suspend fun insertMealsToDb(meals: List<Meal>) {
        meals.forEach {
            if (it is MealEntity) {
                mealDoa.insertMeals(it)
            } else {
                mealDoa.insertMeals(
                    MealEntity(
                        it.idCategory,
                        it.strCategory,
                        it.strCategoryThumb,
                        it.strCategoryDescription
                    )
                )
            }
        }
    }

}
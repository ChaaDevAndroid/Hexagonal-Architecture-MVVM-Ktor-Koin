package com.chaaba.domain.repo

import com.chaaba.domain.ports.network.MealNetworkPort
import com.chaaba.domain.ports.persistence.MealPersistencePort
import com.chaaba.library.Result
import com.chaaba.library.dto.Meal
import com.chaaba.library.repo.MealRepo
import kotlinx.coroutines.flow.Flow

class MealRepoAdapter(
    private val mealNetworkPort: MealNetworkPort,
    private val mealPersistencePort: MealPersistencePort,
) : MealRepo {

    override suspend fun getMealsFromRemote(): Flow<Result<List<Meal>>> =
        mealNetworkPort.getMealsFromRemote()

    override suspend fun getMealsFromLocal(): Flow<Result<List<Meal>>> =
        mealPersistencePort.getMealsFromLocal()

    //Single Source Of Truth (SST)
    override suspend fun getMeals(): Flow<Result<List<Meal>>> {
        var isLocalEmpty = false
        val localList = getMealsFromLocal()
        localList.collect {
            if (it is Result.Success) {
                isLocalEmpty = it.value.isEmpty()
            }
        }
        return if (isLocalEmpty) {
            getMealsFromLocal()
        } else {
            getMealsFromRemote()
        }
    }

    override suspend fun insertMeal(meals: List<Meal>) = mealPersistencePort.insertMealsToDb(meals)


}
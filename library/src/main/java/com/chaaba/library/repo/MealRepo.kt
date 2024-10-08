package com.chaaba.library.repo

import com.chaaba.library.Result
import com.chaaba.library.dto.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepo {
    suspend fun getMealsFromRemote(): Flow<Result<List<Meal>>>
    suspend fun getMealsFromLocal(): Flow<Result<List<Meal>>>
    suspend fun getMeals(): Flow<Result<List<Meal>>>
    suspend fun insertMeal(meals: List<Meal>)
}
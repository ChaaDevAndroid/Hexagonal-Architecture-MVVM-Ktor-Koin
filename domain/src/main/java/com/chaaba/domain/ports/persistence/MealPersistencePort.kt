package com.chaaba.domain.ports.persistence

import com.chaaba.library.Result
import com.chaaba.library.dto.Meal
import kotlinx.coroutines.flow.Flow

interface MealPersistencePort {
    suspend fun getMealsFromLocal(): Flow<Result<List<Meal>>>
    suspend fun insertMealsToDb(meals: List<Meal>)
}
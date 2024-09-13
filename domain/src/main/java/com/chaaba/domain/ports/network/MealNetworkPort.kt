package com.chaaba.domain.ports.network

import com.chaaba.library.ResourceUiState
import com.chaaba.library.Result
import com.chaaba.library.dto.Meal
import kotlinx.coroutines.flow.Flow

interface MealNetworkPort {
    suspend fun getMealsFromRemote(): Flow<Result<List<Meal>>>
}
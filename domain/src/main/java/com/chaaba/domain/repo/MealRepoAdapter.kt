package com.chaaba.domain.repo

import com.chaaba.domain.ports.network.MealNetworkPort
import com.chaaba.library.Result
import com.chaaba.library.dto.Meal
import com.chaaba.library.repo.MealRepo
import kotlinx.coroutines.flow.Flow

class MealRepoAdapter(
    private val mealNetworkPort: MealNetworkPort
) : MealRepo {

    override suspend fun getMealsFromRemote(): Flow<Result<List<Meal>>> =
        mealNetworkPort.getMealsFromRemote()

}
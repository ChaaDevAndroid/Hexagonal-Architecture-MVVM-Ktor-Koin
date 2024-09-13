package com.chaaba.network.adapter

import com.chaaba.domain.ports.network.MealNetworkPort
import com.chaaba.library.Result
import com.chaaba.library.dto.Meal
import com.chaaba.network.poko.MealNetworkResponse
import com.chaaba.network.poko.MealResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single


class MealNetworkAdapter(
    private val httpClient: HttpClient
) : MealNetworkPort {

    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    private val MEAL_ENDPOINT = "categories.php"
    override suspend fun getMealsFromRemote(): Flow<Result<List<Meal>>> = flow {
        try {
            val response = httpClient.get<MealNetworkResponse>("$BASE_URL$MEAL_ENDPOINT")
            emit(Result.Success(response.categories))
        } catch (e: Exception) {
            emit(Result.Failure(e.message, e))
        }

    }.flowOn(Dispatchers.IO)

}


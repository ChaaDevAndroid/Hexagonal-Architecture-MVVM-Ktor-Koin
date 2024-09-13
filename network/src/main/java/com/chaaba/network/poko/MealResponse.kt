package com.chaaba.network.poko

import com.chaaba.library.dto.Meal
import kotlinx.serialization.Serializable

@Serializable
data class MealNetworkResponse(
    val categories: List<MealResponse>
)


@Serializable
data class MealResponse(
    override val idCategory: String,
    override val strCategory: String,
    override val strCategoryThumb: String,
    override val strCategoryDescription: String
) : Meal
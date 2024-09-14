package com.chaaba.persistence.poko

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chaaba.library.dto.Meal

@Entity(tableName = "meals_table")
data class MealEntity(
    @PrimaryKey override val idCategory: String,
    override val strCategory: String,
    override val strCategoryThumb: String,
    override val strCategoryDescription: String
) : Meal
package com.chaaba.persistence.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chaaba.library.dto.Meal
import com.chaaba.persistence.poko.MealEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MealDao {
    @Query("SELECT * FROM meals_table")
    suspend fun getMeals(): List<MealEntity>

    @Insert
    suspend fun insertMeals(meal: MealEntity)

}
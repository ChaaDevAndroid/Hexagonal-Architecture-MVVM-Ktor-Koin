package com.chaaba.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chaaba.persistence.poko.MealEntity

@Database(entities = [MealEntity::class], version = 1, exportSchema = true)
abstract class MDataBase: RoomDatabase() {
    abstract fun getMealsDao(): MealDao
}
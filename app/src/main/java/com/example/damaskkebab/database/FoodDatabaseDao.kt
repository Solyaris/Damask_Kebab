package com.example.damaskkebab.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.damaskkebab.models.Food

@Dao
interface FoodDatabaseDao {
    @Insert
    suspend fun insert(food: Food)

    @Update
    suspend fun update(food: Food)

    @Query("select * from food_table WHERE foodId = :key")
    suspend fun get(key: Long): Food?

    @Query("select * from food_table order by foodId DESC")
    fun getAllFood(): List<Food>

//    fun getAllFood(): LiveData<List<Food>>
}
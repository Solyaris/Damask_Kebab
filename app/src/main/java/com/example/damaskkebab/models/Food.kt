package com.example.damaskkebab.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(
    @ColumnInfo(name = "name")
    val Name: String,
    @ColumnInfo(name = "image")
    val Image: String,
    val MenuId: String,
    @ColumnInfo(name = "description")
    val Description: String,
    @ColumnInfo(name = "price")
    val Price: String,
    @ColumnInfo(name="count")
    var Quantity: Long,
    @PrimaryKey
    var foodId: Long = 0L,

    ) {
    constructor() : this("", "", "", "", "", 1)
}

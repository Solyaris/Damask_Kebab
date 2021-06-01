package com.example.damaskkebab.models

data class Food(
    val Name: String,
    val Image: String,
    val MenuId: String,
    val Description: String,
    val Price: String
) {
    constructor() : this("", "", "", "", "")
}

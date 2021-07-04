package com.example.damaskkebab.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.damaskkebab.database.FoodDatabaseDao

class CartViewModel(val database: FoodDatabaseDao, application: Application) : AndroidViewModel(application) {
    val foods = database.getAllFood()

}


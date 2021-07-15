package com.example.recyclertest.fragments

import java.util.Date

data class ItemModel(
    val title: String,
    val description: String,
    val date: Date,
    var isFavorite: Boolean,
)
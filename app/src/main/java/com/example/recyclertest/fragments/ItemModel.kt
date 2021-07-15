package com.example.recyclertest.fragments

import java.util.*

data class ItemModel(
    val title: String,
    val description: String,
    val date: Date,
    var isFavorite: Boolean,
    val id: String = UUID.randomUUID().toString(),
)
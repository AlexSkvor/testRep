package com.example.recyclertest.fragments.adapters

import com.example.recyclertest.fragments.ItemModel

interface ItemFavoriteClickListener {

    fun onFavoriteClick(item: ItemModel, isFavorite: Boolean)
}
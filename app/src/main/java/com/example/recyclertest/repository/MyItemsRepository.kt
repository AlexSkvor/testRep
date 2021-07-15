package com.example.recyclertest.repository

import androidx.lifecycle.LiveData
import com.example.recyclertest.fragments.ItemModel

interface MyItemsRepository {
    val items: LiveData<List<ItemModel>>

    fun saveNewItem(item: ItemModel)

    fun setFavorite(itemModel: ItemModel, isFavorite: Boolean)

    fun addItemChangeListener(listener: () -> Unit)

    fun removeItemChangeListener(listener: () -> Unit)
}
package com.example.recyclertest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclertest.fragments.ItemModel
import java.util.Calendar

class MyItemsRepositoryImpl private constructor() : MyItemsRepository {
    private val startingItems: List<ItemModel> = listOf(
        ItemModel(
            title = "Chapter 1",
            description = "This chapter will tell us about something strange, horrible and fantastic, beautiful and peaceful.",
            date = Calendar.getInstance().apply { set(2021, 0, 8) }.time,
            isFavorite = false
        ),
        ItemModel(
            title = "Chapter 2",
            description = "This chapter will tell us about something strange, horrible and fantastic!",
            date = Calendar.getInstance().apply { set(2021, 2, 6) }.time,
            isFavorite = false
        ),
        ItemModel(
            title = "Chapter 3",
            description = "This chapter will tell us about something strange, horrible and fantastic, differ and deeper, honorably and beautiful, lovely and funny.",
            date = Calendar.getInstance().apply { set(2021, 3, 26) }.time,
            isFavorite = false
        ),
        ItemModel(
            title = "Chapter 4",
            description = "This chapter will tell us about something horrible.",
            date = Calendar.getInstance().apply { set(2021, 3, 29) }.time,
            isFavorite = false
        ),
        ItemModel(
            title = "Chapter 5",
            description = "This chapter will tell us about something strange, horrible and fantastic, enthusiastic!",
            date = Calendar.getInstance().apply { set(2021, 8, 11) }.time,
            isFavorite = false
        ),
        ItemModel(
            title = "Chapter 6",
            description = "This chapter will tell us about something fantastic, funny, lovely, erudition, peaceful.",
            date = Calendar.getInstance().apply { set(2021, 9, 19) }.time,
            isFavorite = false
        ),
        ItemModel(
            title = "Chapter 7",
            description = "This chapter will tell us about something strange, horrible and fantastic, beautiful!",
            date = Calendar.getInstance().apply { set(2021, 10, 31) }.time,
            isFavorite = false
        ),
        ItemModel(
            title = "Chapter 8",
            description = "This chapter will tell us about something strange, horrible and peaceful.",
            date = Calendar.getInstance().apply { set(2021, 5, 27) }.time,
            isFavorite = false
        ),
        ItemModel(
            title = "Chapter 9",
            description = "This chapter will tell us about something strange, horrible.",
            date = Calendar.getInstance().apply { set(2021, 4, 2) }.time,
            isFavorite = false
        ),
        ItemModel(
            title = "Chapter 10",
            description = "This chapter will tell us about something strange, horrible and fantastic.",
            date = Calendar.getInstance().apply { set(2021, 4, 14) }.time,
            isFavorite = false
        )
    )

    private val listeners = mutableListOf<() -> Unit>()

    private val _itemsLiveData = MutableLiveData(startingItems)
    override val items: LiveData<List<ItemModel>> = _itemsLiveData

    override fun setFavorite(itemModel: ItemModel, isFavorite: Boolean) {
        itemModel.isFavorite = isFavorite
        listeners.forEach { it.invoke() }
    }

    override fun addItemChangeListener(listener: () -> Unit) {
        listeners.add(listener)
    }

    override fun removeItemChangeListener(listener: () -> Unit) {
        listeners.remove(listener)
    }

    override fun saveNewItem(item: ItemModel) {
        val oldList = items.value.orEmpty()
        val newList = oldList + item
        _itemsLiveData.postValue(newList)
    }

    companion object {
        private var instance: MyItemsRepository? = null

        fun getInstance(): MyItemsRepository {
            if (instance == null) {
                instance = MyItemsRepositoryImpl()
            }
            return instance!!
        }
    }
}
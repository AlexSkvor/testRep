package com.example.recyclertest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclertest.fragments.ItemModel
import com.example.recyclertest.repository.MyItemsRepository
import com.example.recyclertest.repository.MyItemsRepositoryImpl

class NewItemViewModel(
    private val repository: MyItemsRepository
) : ViewModel() {

    fun saveNewItem(item: ItemModel) = repository.saveNewItem(item)

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewItemViewModel(MyItemsRepositoryImpl.getInstance()) as T
        }
    }
}
package com.example.recyclertest.fragments.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclertest.fragments.ItemModel
import com.example.recyclertest.repository.MyItemsRepository
import com.example.recyclertest.repository.MyItemsRepositoryImpl

class ContentViewModel(
    private val repository: MyItemsRepository
) : ViewModel() {

    private val _items: MutableLiveData<List<ItemModel>> = MutableLiveData()
    val items: LiveData<List<ItemModel>>
        get() = _items

    var isFavorite: Boolean = false

    private val itemChangeListener = { getItems() }

    init {
        repository.addItemChangeListener(itemChangeListener)
    }

    fun changeFavoriteState(item: ItemModel, favorite: Boolean) {
        repository.setFavorite(item, favorite)
    }

    fun getItems() {
        _items.value = if (isFavorite) {
            repository.items.filter { it.isFavorite }
        } else {
            repository.items
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.removeItemChangeListener(itemChangeListener)
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ContentViewModel(MyItemsRepositoryImpl.getInstance()) as T
        }
    }
}
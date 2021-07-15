package com.example.recyclertest.fragments.content

import androidx.lifecycle.*
import com.example.recyclertest.fragments.ItemModel
import com.example.recyclertest.repository.MyItemsRepository
import com.example.recyclertest.repository.MyItemsRepositoryImpl

class ContentViewModel(
    private val repository: MyItemsRepository
) : ViewModel() {

    val items: LiveData<List<ItemModel>> by lazy {
        if (isFavorite)
            Transformations.map(repository.items) {
                it.filter { item -> item.isFavorite }
            }
        else repository.items
    }

    var isFavorite: Boolean = false

    fun changeFavoriteState(item: ItemModel, favorite: Boolean) {
        repository.setFavorite(item, favorite)
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ContentViewModel(MyItemsRepositoryImpl.getInstance()) as T
        }
    }
}
package com.example.recyclertest.fragments.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclertest.R
import com.example.recyclertest.fragments.ItemModel
import com.example.recyclertest.fragments.adapters.ItemFavoriteClickListener
import com.example.recyclertest.fragments.adapters.MyItemModelsAdapter
import kotlinx.android.synthetic.main.fragment_all.*

class ContentFragment : Fragment(), ItemFavoriteClickListener {

    private val viewModel: ContentViewModel by viewModels(factoryProducer = {
        ContentViewModel.Factory()
    })

    private var adapter: MyItemModelsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isFavorite = arguments?.getBoolean(ONLY_FAVORITES_KEY, false) ?: false
        viewModel.isFavorite = isFavorite

        viewModel.items.observe(this) {
            adapter?.items = it
        }

        recyclerView?.layoutManager = LinearLayoutManager(context)

        adapter = MyItemModelsAdapter(this)
        recyclerView?.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onFavoriteClick(item: ItemModel, isFavorite: Boolean) {
        viewModel.changeFavoriteState(item, isFavorite)
    }

    companion object {
        private const val ONLY_FAVORITES_KEY = "onlyFavorites"
        fun newInstance(onlyFavorites: Boolean): ContentFragment {
            return ContentFragment().apply {
                arguments = bundleOf(ONLY_FAVORITES_KEY to onlyFavorites)
            }
        }
    }
}
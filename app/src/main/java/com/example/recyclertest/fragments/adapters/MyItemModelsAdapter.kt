package com.example.recyclertest.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclertest.R
import com.example.recyclertest.fragments.ItemModel
import java.text.DateFormat

class MyItemModelsAdapter(private val itemFavoriteClickListener: ItemFavoriteClickListener) :
    RecyclerView.Adapter<MyItemModelsAdapter.MyItemViewHolder>() {

    var items: List<ItemModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyItemModelsAdapter.MyItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent, false)
        return MyItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyItemModelsAdapter.MyItemViewHolder, position: Int) {
        val item = items[position]
        holder.itemTitle.text = item.title
        holder.itemDescription.text = item.description
        holder.itemDate.text = DateFormat.getDateInstance().format(item.date)
        holder.itemLike.isChecked = item.isFavorite
        holder.itemLike.setOnClickListener {
            itemFavoriteClickListener.onFavoriteClick(item, isFavorite = !item.isFavorite)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView
        var itemDescription: TextView
        var itemDate: TextView
        var itemLike: CheckBox

        init {
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDescription = itemView.findViewById(R.id.itemDescription)
            itemDate = itemView.findViewById(R.id.itemDate)
            itemLike = itemView.findViewById(R.id.itemLike)
        }
    }
}
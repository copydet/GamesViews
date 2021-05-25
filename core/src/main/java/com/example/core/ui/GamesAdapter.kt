package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemListGamesBinding
import com.example.core.domain.model.Games
import java.util.ArrayList


@Suppress("DEPRECATION")
class GamesAdapter : RecyclerView.Adapter<GamesAdapter.ListViewHolder>() {

    private var listData = ArrayList<Games>()
    var onItemClick: ((Int) -> Unit)? = null

    fun setData(newListData: List<Games>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemListGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder){
            with(listData[position]){
                Glide.with(itemView.context)
                    .load(image)
                    .into(binding.ivItemImage)
                binding.tvPlaytime.text = itemView.context.getString(R.string.hours, playtime)
                binding.tvNameHome.text = name
                binding.tvReleasedHome.text = released
                holder.itemView.setOnClickListener {
                    onItemClick?.invoke(id)
                }
            }
        }
    }

    inner class ListViewHolder(val binding: ItemListGamesBinding): RecyclerView.ViewHolder(binding.root)

}
package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemListGamesBinding
import com.example.core.domain.model.Games

class WishListAdapter : PagedListAdapter<Games, WishListAdapter.WishListViewHolder>(callBack) {

    companion object{
        private val callBack = object : DiffUtil.ItemCallback<Games>(){
            override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean {
                return oldItem == newItem
            }
        }
    }
    var onItemClick: ((Int) -> Unit)? = null

    inner class WishListViewHolder(val binding: ItemListGamesBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder = WishListViewHolder(
        ItemListGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        val gameWishlist = getItem(position)
        gameWishlist?.let {
            with(holder){
                with(it){
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
    }
}
package com.example.gamesviews.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.core.data.Resource
import com.example.gamesviews.R
import com.example.gamesviews.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {
    private var wishlist: Boolean = false

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailGamesViewModel: DetailGamesViewModel by viewModel()
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gamesId = intent.extras?.getInt(EXTRA_DATA) ?: return

        detailGamesViewModel.detailGames(gamesId)
        detailGamesViewModel.games.observe(this,{
            if (it != null){
                when(it){
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        it.let {
                            supportActionBar?.title = it.data?.name
                            with(binding){
                                contentDetail.tvReleased.text = it.data?.released
                                contentDetail.description.text = it.data?.description
                                contentDetail.tvUpdate.text = it.data?.lastUpdate
                                contentDetail.tvRatings.text = it.data?.rating.toString()
                                contentDetail.websiteLink.text = it.data?.website

                                Glide.with(this@DetailActivity)
                                    .load(it.data?.image)
                                    .into(contentDetail.profileImage)

                                Glide.with(this@DetailActivity)
                                    .load(it.data?.backgroundImage)
                                    .into(backgroundImage)

                            }
                            binding.toolbarTv.text = it.data?.name
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        })
        detailGamesViewModel.wishlistGames(gamesId).observe(this, {
            wishlist = it != null && it.id == gamesId
            setStatusWishList(wishlist)
        })
        binding.wishlist.setOnClickListener {
            wishlist = !wishlist
            setStatusWishList(wishlist)
            if (wishlist){
                detailGamesViewModel.insertWishlist(gamesId)
            }else {
                detailGamesViewModel.deleteWishlist(gamesId)
            }
        }
    }

    private fun setStatusWishList(statusWishList: Boolean) {
        if (statusWishList){
            binding.wishlist.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_library_add_check_24))
        }else {
            binding.wishlist.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_library_add_24))
        }
    }
}
package com.example.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GamesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class WishListViewModel(
    private val gamesUseCase: GamesUseCase,
    private val coroutineDispatcher: CoroutineDispatcher) : ViewModel() {
    val gamesWishList = gamesUseCase.getListWishlistGames().asLiveData()

    fun deleteWishlist(wishlistId: Int) = viewModelScope.launch(coroutineDispatcher){
        gamesUseCase.deleteWishList(wishlistId)
    }
}
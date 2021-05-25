package com.example.gamesviews.ui.detail

import androidx.lifecycle.*
import com.example.core.domain.usecase.GamesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailGamesViewModel(
    private val gamesUseCase: GamesUseCase,
    private val coroutineDispatcher: CoroutineDispatcher): ViewModel() {
    private val gamesId = MutableLiveData<Int>()

    fun detailGames(gamesId: Int) {
        this.gamesId.value = gamesId
    }
    val games = Transformations.switchMap(gamesId){
        gamesUseCase.getDetailGames(it).asLiveData()
    }

    fun wishlistGames(wishlistId: Int) =
        gamesUseCase.getWishListGame(wishlistId).asLiveData()

    fun insertWishlist(wishlistId: Int) = viewModelScope.launch(coroutineDispatcher){
        gamesUseCase.insertWishList(wishlistId)
    }
    fun deleteWishlist(wishlistId: Int) = viewModelScope.launch(coroutineDispatcher){
        gamesUseCase.deleteWishList(wishlistId)
    }
}
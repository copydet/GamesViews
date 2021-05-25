package com.example.core.domain.usecase

import androidx.paging.PagedList
import com.example.core.data.Resource
import com.example.core.domain.model.Games
import com.example.core.domain.model.WishListGames
import kotlinx.coroutines.flow.Flow

interface GamesUseCase {
    fun getAllGames(): Flow<Resource<List<Games>>>
    fun getDetailGames(gamesId: Int): Flow<Resource<Games>>
    fun getWishListGame(wishlistId: Int): Flow<WishListGames?>
    fun getListWishlistGames(): Flow<PagedList<Games>>
    suspend fun insertWishList(wishlistId: Int)
    suspend fun deleteWishList(wishlistId: Int)
}
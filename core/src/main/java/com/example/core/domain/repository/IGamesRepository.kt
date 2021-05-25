package com.example.core.domain.repository

import androidx.paging.PagedList
import com.example.core.data.Resource
import com.example.core.domain.model.Games
import com.example.core.domain.model.WishListGames
import kotlinx.coroutines.flow.Flow

interface IGamesRepository {
    fun getAllGames(): Flow<Resource<List<Games>>>

    fun getDetailGames(gamesId: Int): Flow<Resource<Games>>

    fun getListWishlistGames(): Flow<PagedList<Games>>

    fun getWishListGame(wishlistId: Int): Flow<WishListGames?>

    suspend fun insertWishList(wishlistId: Int)

    suspend fun deleteWishList(wishlistId: Int)

}
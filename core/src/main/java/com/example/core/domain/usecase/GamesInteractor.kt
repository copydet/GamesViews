package com.example.core.domain.usecase

import androidx.paging.PagedList
import com.example.core.data.Resource
import com.example.core.domain.model.Games
import com.example.core.domain.model.WishListGames
import com.example.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val gameRepository: IGamesRepository):
    GamesUseCase {

    override fun getAllGames() = gameRepository.getAllGames()

    override fun getWishListGame(wishlistId : Int): Flow<WishListGames?> = gameRepository.getWishListGame(wishlistId)

    override fun getListWishlistGames(): Flow<PagedList<Games>> = gameRepository.getListWishlistGames()

    override fun getDetailGames(gamesId: Int): Flow<Resource<Games>> = gameRepository.getDetailGames(gamesId)

    override suspend fun insertWishList(wishlistId: Int) =
        gameRepository.insertWishList(wishlistId)

    override suspend fun deleteWishList(wishlistId: Int) =
        gameRepository.deleteWishList(wishlistId)
}
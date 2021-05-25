package com.example.core.data.source.local

import androidx.paging.DataSource
import com.example.core.data.source.local.entity.GamesEntity
import com.example.core.data.source.local.entity.WishListEntity
import com.example.core.data.source.local.room.GamesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gamesDao: GamesDao) {

    fun getAllGames(): Flow<List<GamesEntity>> = gamesDao.getAllGames()

    suspend fun insertGames(games: List<GamesEntity>) = gamesDao.insertGames(games)

    fun getDetailGames(id: Int): Flow<GamesEntity> = gamesDao.getDetailGames(id)

    suspend fun updateDetailGames(gamesDetail: GamesEntity) = gamesDao.updateDetailGames(gamesDetail)

    fun getWishList(id: Int): Flow<WishListEntity> = gamesDao.getWishList(id)

    fun getListWishlist(): DataSource.Factory<Int, GamesEntity> = gamesDao.getListWishlist()

    suspend fun insertWishList(wishListGames: WishListEntity) = gamesDao.insertWishList(wishListGames)

    suspend fun deleteWishList(wishListGames: WishListEntity) = gamesDao.deleteWishList(wishListGames)
}
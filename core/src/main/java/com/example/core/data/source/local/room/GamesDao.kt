package com.example.core.data.source.local.room

import androidx.room.*
import androidx.paging.DataSource
import com.example.core.data.source.local.entity.WishListEntity
import com.example.core.data.source.local.entity.GamesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
    @Query("SELECT * FROM games order by id desc")
    fun getAllGames(): Flow<List<GamesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GamesEntity>)

    @Query("SELECT * FROM games where id = :id")
    fun getDetailGames(id: Int): Flow<GamesEntity>

    @Update
    suspend fun updateDetailGames(games: GamesEntity)

    @Query("SELECT * FROM games a join wishList_games b on a.id = b.wishListId")
    fun getListWishlist(): DataSource.Factory<Int, GamesEntity>

    @Query("SELECT * FROM wishList_games where wishListId = :id")
    fun getWishList(id: Int): Flow<WishListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishList(wishList: WishListEntity)

    @Delete
    suspend fun deleteWishList(wishList: WishListEntity)
}
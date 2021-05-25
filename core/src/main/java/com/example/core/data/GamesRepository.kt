package com.example.core.data

import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.entity.WishListEntity
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.domain.model.Games
import com.example.core.domain.model.WishListGames
import com.example.core.domain.repository.IGamesRepository
import com.example.core.utils.DataMapper
import com.example.core.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GamesRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): IGamesRepository {

    override fun getAllGames(): Flow<Resource<List<Games>>> = networkBoundResource(
        {
            localDataSource.getAllGames().map {
                DataMapper.mapListEntitiesToDomain(it)
            }
        },
        { remoteDataSource.getAllGames() },
        {
            val gamesList = DataMapper.mapListResponseToEntities(it.results)
            localDataSource.insertGames(gamesList)
        }
    )

    override fun getDetailGames(gamesId: Int): Flow<Resource<Games>> = networkBoundResource(
        {
            localDataSource.getDetailGames(gamesId).map {
                DataMapper.mapGamesEntitiesToDomain(it)
            }
        },
        {remoteDataSource.getDetailGames(gamesId)},
        {
            val gameDetail = DataMapper.mapGamesResponseToEntities(it)
            localDataSource.updateDetailGames(gameDetail)
        }
    )

    override fun getListWishlistGames(): Flow<PagedList<Games>> = LivePagedListBuilder(
        localDataSource.getListWishlist().map { DataMapper.mapGamesEntitiesToDomain(it)},
        PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(20)
            .setPageSize(20).build()
    ).build().asFlow()

    override fun getWishListGame(wishlistId: Int): Flow<WishListGames?> =
        localDataSource.getWishList(wishlistId).map {
            DataMapper.mapWishlistGameEntitiesToDomain(it)
        }

    override suspend fun insertWishList(wishlistId: Int) =
        localDataSource.insertWishList(WishListEntity(wishlistId))

    override suspend fun deleteWishList(wishlistId: Int) =
        localDataSource.deleteWishList(WishListEntity(wishlistId))
}
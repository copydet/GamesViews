package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers

class RemoteDataSource(private val apiService: ApiService): BaseDataSource(){

    suspend fun getAllGames() = getResult({ apiService.getGamesList(key = "69946fa0064f48d5a6b1b84d77e30923")}, Dispatchers.IO)

    suspend fun getDetailGames(gamesId: Int) = getResult({ apiService.getDetailGames(gamesId, key = "69946fa0064f48d5a6b1b84d77e30923") }, Dispatchers.IO )
}
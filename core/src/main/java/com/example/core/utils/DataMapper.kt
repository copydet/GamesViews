package com.example.core.utils

import com.example.core.data.source.local.entity.GamesEntity
import com.example.core.data.source.local.entity.WishListEntity
import com.example.core.data.source.remote.response.DetailGamesResponse
import com.example.core.data.source.remote.response.ResultsItem
import com.example.core.domain.model.Games
import com.example.core.domain.model.WishListGames

object DataMapper {
    fun mapListResponseToEntities(input: List<ResultsItem>): List<GamesEntity> = input.map {
        val gamesList = ArrayList<GamesEntity>()
        input.map{
            val games = GamesEntity(
                id = it.id,
                name = it.name,
                description = null,
                released = it.released,
                lastUpdate = null,
                image = it.backgroundImage,
                backgroundImage = null,
                playtime = it.playtime,
                rating = it.rating,
                website = null,
            )
            gamesList.add(games)
        }
        return gamesList
    }
    fun mapListEntitiesToDomain(input: List<GamesEntity>): List<Games> =
        input.map {
        Games(
            id = it.id,
            name = it.name,
            description = it.description,
            released = it.released,
            lastUpdate = it.lastUpdate,
            image = it.image,
            backgroundImage = it.backgroundImage,
            playtime = it.playtime,
            rating = it.rating,
            website = it.website,
        )
    }
    fun mapGamesResponseToEntities(input: DetailGamesResponse): GamesEntity =
        GamesEntity(
            id = input.id,
            name = input.name,
            description = input.description,
            released = input.released,
            lastUpdate = input.updated,
            image = input.backgroundImage,
            backgroundImage = input.backgroundImageAdditional,
            playtime = input.playtime,
            rating = input.rating,
            website = input.website,

    )
    fun mapGamesEntitiesToDomain(input:GamesEntity): Games = Games(
        id = input.id,
        name = input.name,
        description = input.description,
        released = input.released,
        lastUpdate = input.lastUpdate,
        image = input.image,
        backgroundImage = input.backgroundImage,
        playtime = input.playtime,
        rating = input.rating,
        website = input.website,
    )
    fun mapWishlistGameEntitiesToDomain(input: WishListEntity?): WishListGames? {
        return if (input != null){
            WishListGames(
                id = input.id
            )
        }else {
            null
        }
    }
}
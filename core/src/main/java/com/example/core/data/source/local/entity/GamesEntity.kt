package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GamesEntity (
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: Int,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "description")
        var description: String?,

        @ColumnInfo(name = "release")
        var released: String,

        @ColumnInfo(name = "updated")
        var lastUpdate: String?,

        @ColumnInfo(name = "background_image")
        var image: String,

        @ColumnInfo(name = "background_image_additional")
        var backgroundImage: String?,

        @ColumnInfo(name = "website")
        var website: String?,

        @ColumnInfo(name = "rating")
        var rating: Double,

        @ColumnInfo(name = "playtime")
        var playtime: Int?,

        )
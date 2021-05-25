package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.WishListEntity
import com.example.core.data.source.local.entity.GamesEntity

@Database(entities = [GamesEntity::class, WishListEntity::class], version = 1, exportSchema = false)

abstract class GamesDatabase: RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}
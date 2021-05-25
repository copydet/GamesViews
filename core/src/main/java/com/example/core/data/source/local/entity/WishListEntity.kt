package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishList_games")
data class WishListEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "wishListId")
    var id: Int
        )
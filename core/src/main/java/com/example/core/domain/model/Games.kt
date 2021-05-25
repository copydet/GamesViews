package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games (
    val id: Int,
    var name: String?,
    var description: String?,
    var released: String?,
    var lastUpdate: String?,
    var image: String?,
    var backgroundImage: String?,
    var website: String?,
    var rating: Double,
    var playtime: Int?,
        ):Parcelable
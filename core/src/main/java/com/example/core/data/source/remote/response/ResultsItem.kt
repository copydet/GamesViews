package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResultsItem(

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("playtime")
    val playtime: Int
)
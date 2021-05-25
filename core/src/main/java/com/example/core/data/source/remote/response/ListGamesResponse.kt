package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGamesResponse(

    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("previous")
    val previous: Any,

    @field:SerializedName("results")
    val results: List<ResultsItem>
)
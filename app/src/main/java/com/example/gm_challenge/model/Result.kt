package com.example.gm_challenge.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val artistName: String?,
    val primaryGenreName: String?,
    val releaseDate: String?,
    val trackName: String?,
    val trackPrice: Double?,
)
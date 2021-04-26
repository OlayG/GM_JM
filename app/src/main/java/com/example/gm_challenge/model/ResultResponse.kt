package com.example.gm_challenge.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultResponse(
    val resultCount: Int,
    val results: List<Result>
)
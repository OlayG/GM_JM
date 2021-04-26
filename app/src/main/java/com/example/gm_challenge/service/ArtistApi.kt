package com.example.gm_challenge.service

import com.example.gm_challenge.model.ResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistApi {

    @GET("search")
    suspend fun fetchArtists(
        @Query("term") artistName: String
    ) : Response<ResultResponse>
}
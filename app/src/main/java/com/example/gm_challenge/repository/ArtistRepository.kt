package com.example.gm_challenge.repository

import com.example.gm_challenge.model.Result
import com.example.gm_challenge.service.ArtistApi
import com.example.gm_challenge.util.Resource
import javax.inject.Inject

class ArtistRepository  @Inject constructor(
    private val artistApi: ArtistApi
) {

    suspend fun fetchArtists(artistName: String): Resource<List<Result>> {
        return if(artistName.isEmpty()) {
            Resource.Error("Enter artist name")
        } else {
            val response = artistApi.fetchArtists(artistName)
            return response.body()?.let {
                Resource.Success(it.results)
            } ?: Resource.Error("Your request produces an error")
        }
    }
}
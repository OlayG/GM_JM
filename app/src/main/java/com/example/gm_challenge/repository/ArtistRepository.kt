package com.example.gm_challenge.repository

import com.example.gm_challenge.service.ArtistApi
import com.example.gm_challenge.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistRepository @Inject constructor(private val artistApi: ArtistApi) {

    fun fetchArtists(artistName: String) = flow {
        emit(Resource.Loading)
        val resource = if (artistName.isBlank()) {
            Resource.Error("Enter artist name")
        } else {
            val response = artistApi.fetchArtists(artistName)
            if (response.isSuccessful) {
                val results = response.body()?.results
                if (results != null && results.isNotEmpty()) Resource.Success(results)
                else Resource.Error("No results found for $artistName")
            } else Resource.Error("Your request produces an error")
        }
        emit(resource)
    }
}
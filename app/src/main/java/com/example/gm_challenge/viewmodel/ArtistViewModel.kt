package com.example.gm_challenge.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gm_challenge.model.Result
import com.example.gm_challenge.repository.ArtistRepository
import com.example.gm_challenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val artistRepository: ArtistRepository
) : ViewModel() {

    private val _artists = MutableLiveData<Resource<List<Result>>>()
    val artists : LiveData<Resource<List<Result>>> get() = _artists

    var searchQuery: ObservableField<String> = ObservableField("")

    val fetch : Function0<Unit> = this::fetchArtists

    private fun fetchArtists() {
        _artists.postValue(Resource.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            searchQuery.get()?.let { artistName ->
                _artists.postValue(artistRepository.fetchArtists(artistName))
            }
        }
    }

}
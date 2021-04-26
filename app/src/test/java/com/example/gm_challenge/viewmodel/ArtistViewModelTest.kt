package com.example.gm_challenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gm_challenge.model.Result
import com.example.gm_challenge.repository.ArtistRepository
import com.example.gm_challenge.util.Resource
import com.example.gm_challenge.util.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class ArtistViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val artistRepo = mockk<ArtistRepository>()

    private lateinit var artistViewModel: ArtistViewModel

    @Before
    fun setup() {
        artistViewModel = ArtistViewModel(artistRepo)
    }

    @Test
    fun validQuery_returnsSuccess() {

        // When searching with valid query
        val validQuery = "Ace"
        artistViewModel.searchQuery.value = validQuery
        every { artistRepo.fetchArtists(validQuery) } returns flow {
            emit(Resource.Success<List<Result>>(listOf()))
        }

        // Then resource is in success state
        val resource = artistViewModel.artists.getOrAwaitValue()
        assertThat(resource is Resource.Success, `is`(true))
    }

    @Test
    fun invalidQuery_returnsError() {

        // When searching with invalid query
        val invalidQuery = "ahsodbvfsdujbf"
        artistViewModel.searchQuery.value = invalidQuery
        every { artistRepo.fetchArtists(invalidQuery) } returns flow { emit(Resource.Error("")) }

        // Then resource is in error state
        val resource = artistViewModel.artists.getOrAwaitValue()
        assertThat(resource is Resource.Error, `is`(true))
    }
}
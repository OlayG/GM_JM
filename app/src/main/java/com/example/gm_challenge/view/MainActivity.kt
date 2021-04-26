package com.example.gm_challenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.gm_challenge.R
import com.example.gm_challenge.adapter.ArtistAdapter
import com.example.gm_challenge.databinding.ActivityMainBinding
import com.example.gm_challenge.util.Resource
import com.example.gm_challenge.viewmodel.ArtistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val artistAdapter: ArtistAdapter by lazy { ArtistAdapter() }
    private val artistViewModel : ArtistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDataBinding()
        setupObservers()
    }

    private fun setupDataBinding() {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            lifecycleOwner = this@MainActivity
            viewModel = artistViewModel
            adapter = artistAdapter
        }
    }

    private fun setupObservers() = with(artistViewModel) {
        artists.observe(this@MainActivity) { result ->
            if(result is Resource.Success) artistAdapter.setList(result.data)
        }
    }
}
package com.example.gm_challenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gm_challenge.databinding.ArtistItemBinding
import com.example.gm_challenge.model.Result

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    private val dataSet = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.load(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun setList(results: List<Result>) {
        dataSet.clear()
        dataSet.addAll(results)
        notifyDataSetChanged()
    }

    class ArtistViewHolder(private val binding: ArtistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun load(result: Result) = with(binding) {
            artist = result
            executePendingBindings()
        }
    }
}
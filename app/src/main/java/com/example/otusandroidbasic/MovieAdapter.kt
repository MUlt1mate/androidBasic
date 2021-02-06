package com.example.otusandroidbasic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(
    private val items: List<MovieData>,
    private val clickListener: DetailsClickListener
) : RecyclerView.Adapter<MovieVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return MovieVH(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.detailsButton.setOnClickListener {
            clickListener.onDetailsClick(item)
        }
    }

    interface DetailsClickListener {
        fun onDetailsClick(movieItem: MovieData)
    }
}
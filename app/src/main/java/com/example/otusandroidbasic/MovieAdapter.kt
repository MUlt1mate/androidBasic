package com.example.otusandroidbasic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class MovieAdapter(
    protected val movieRepository: IMovieRepository,
    private val clickListener: DetailsClickListener
) : RecyclerView.Adapter<MovieVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return MovieVH(view)
    }

    override fun getItemCount() = movieRepository.getAll().size

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val favoriteMovies = movieRepository.getFavorite()
        val items = movieRepository.getAll()
        bindVH(holder, items[position], favoriteMovies.contains(items[position].title), position)
    }

    protected fun bindVH(
        holder: MovieVH,
        item: MovieData,
        isFavorite: Boolean,
        position: Int
    ) {
        holder.bind(item, isFavorite)
        holder.detailsButton.setOnClickListener {
            clickListener.onDetailsClick(item)
        }
        holder.markFavoriteButton.setOnClickListener {
            holder.changeFavorite()
            clickListener.onFavoriteClick(item, holder.favorite, position)
        }
    }

    interface DetailsClickListener {
        fun onDetailsClick(movieItem: MovieData)
        fun onFavoriteClick(movieItem: MovieData, added: Boolean, position: Int)
    }
}
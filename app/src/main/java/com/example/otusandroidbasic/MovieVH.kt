package com.example.otusandroidbasic

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieVH(movieItemView: View) : RecyclerView.ViewHolder(movieItemView) {
    private val titleTv: TextView = movieItemView.findViewById(R.id.posterTitle)
    private val image: ImageView = movieItemView.findViewById(R.id.posterImage)
    var favorite: Boolean = false
    val detailsButton: TextView = movieItemView.findViewById(R.id.movieButton)
    val markFavoriteButton: TextView = movieItemView.findViewById(R.id.markFavorite)

    fun bind(item: MovieData, isFavorite: Boolean) {
        favorite = isFavorite
        setFavorite()
        titleTv.setText(item.title)
        image.setImageResource(item.img)
    }

    fun changeFavorite() {
        favorite = !favorite
    }

    private fun setFavorite() {
        if (favorite) {
            markFavoriteButton.setText(R.string.inFavorite)
        } else {
            markFavoriteButton.setText(R.string.addToFavorite)
        }
    }
}
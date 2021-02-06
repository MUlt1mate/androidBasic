package com.example.otusandroidbasic

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieVH(movieItemView: View) : RecyclerView.ViewHolder(movieItemView) {
    private val titleTv: TextView = movieItemView.findViewById(R.id.posterTitle)
    private val image: ImageView = movieItemView.findViewById(R.id.posterImage)

    fun bind(item: MovieData) {
        titleTv.setText(item.title)
        image.setImageResource(item.img)
    }
}
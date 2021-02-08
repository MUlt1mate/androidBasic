package com.example.otusandroidbasic

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Favorites : AppCompatActivity() {
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.movieList) }
    private val movieRepository = getRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        initRecycler()
    }

    private fun initRecycler() {
        val orientation = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LinearLayoutManager.HORIZONTAL
            else -> LinearLayoutManager.VERTICAL
        }
        val layoutManager = LinearLayoutManager(this, orientation, false)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter =
            FavoriteMovieAdapter(
                movieRepository,
                object : MovieAdapter.DetailsClickListener {
                    override fun onDetailsClick(movieItem: MovieData) =
                        showDetail(this@Favorites, movieItem)

                    override fun onFavoriteClick(
                        movieItem: MovieData,
                        added: Boolean,
                        position: Int
                    ) {
                        movieRepository.removeFromFavorite(movieItem.title)
                        recyclerView.adapter?.notifyDataSetChanged()
                    }
                })
    }
}
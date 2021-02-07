package com.example.otusandroidbasic

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.movieList) }
    private val movieRepository = MovieRepository()

    companion object {
        const val EXTRA_FAVORITES = "EXTRA_FAVORITES"
    }

    // обработка выбора фильма из списка
    private fun showDetail(data: MovieData) {
        Log.i(tag, "click on movie ${data.title}")

        // открываем экран с подробной информацией
        Intent(this, MovieDescription::class.java).apply {
            putExtra(MovieDescription.MOVIE_DATA, data)
            startActivity(this)
        }
    }

    private fun initRecycler() {
        val orientation = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LinearLayoutManager.HORIZONTAL
            else -> LinearLayoutManager.VERTICAL
        }
        val layoutManager = LinearLayoutManager(this, orientation, false)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter =
            MovieAdapter(
                movieRepository.getAll(),
                movieRepository.getFavorite(),
                object : MovieAdapter.DetailsClickListener {
                    override fun onDetailsClick(movieItem: MovieData) = showDetail(movieItem)
                    override fun onFavoriteClick(movieItem: MovieData, position: Int) {
                        movieRepository.addToFavorite(movieItem.title)
                        recyclerView.adapter?.notifyItemChanged(position)
                    }
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(tag, "created")

        intent.getIntArrayExtra(EXTRA_FAVORITES)?.let {
            Log.i(tag, "restoring favorites")
            movieRepository.setFavorite(it.toSet())
        }

        initRecycler()
    }

    override fun onStop() {
        Log.i(tag, "saving favorites")
        val toTypedArray = movieRepository.getFavorite().toIntArray()
        intent.putExtra(EXTRA_FAVORITES, toTypedArray)
        super.onStop()
    }
}
package com.example.otusandroidbasic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MovieDescription : AppCompatActivity() {
    private val tag = "MovieDescription"

    companion object {
        const val MOVIE_DATA = "MOVIE_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)

        val it = intent.getParcelableExtra<MovieData>(MOVIE_DATA)
        if (it == null) {
            Log.w(tag, "movie data not found")
            return
        }
        Log.i(tag, "received movie data ${it.title}")

        findViewById<TextView>(R.id.movieTitle).setText(it.title)
        findViewById<TextView>(R.id.movieDescription).setText(it.description)
        findViewById<ImageView>(R.id.detailsPoster).setImageResource(it.img)
    }
}

// обработка выбора фильма из списка
fun showDetail(context: Context, data: MovieData) {
    // открываем экран с подробной информацией
    Intent(context, MovieDescription::class.java).apply {
        putExtra(MovieDescription.MOVIE_DATA, data)
        context.startActivity(this)
    }
}

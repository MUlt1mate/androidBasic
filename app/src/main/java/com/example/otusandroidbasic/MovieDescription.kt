package com.example.otusandroidbasic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
        val title = it.title
        Log.i(tag, "received movie data $title")

        findViewById<TextView>(R.id.movieTitle).setText(title)
        findViewById<TextView>(R.id.movieDescription).setText(it.description)
        findViewById<ImageView>(R.id.detailsPoster).setImageResource(it.img)

        findViewById<View>(R.id.shareBtn).setOnClickListener {
            Log.i(tag, "clicked share")
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Check out ${getString(title)} in my app")
            }

            startActivity(Intent.createChooser(sendIntent, null))
        }
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

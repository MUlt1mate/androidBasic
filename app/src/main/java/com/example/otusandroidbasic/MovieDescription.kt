package com.example.otusandroidbasic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ShareActionProvider
import android.widget.TextView

class MovieDescription : AppCompatActivity() {
    companion object {
        const val MOVIE_DATA = "MOVIE_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)

        findViewById<View>(R.id.shareBtn).setOnClickListener {
            val intent = Intent(this, ShareActionProvider::class.java)
            startActivity(intent)
        }

        intent.getParcelableExtra<MovieData>(MOVIE_DATA)?.let {
            findViewById<TextView>(R.id.movieTitle).text = it.title
            findViewById<TextView>(R.id.movieDescription).text = it.description
            findViewById<ImageView>(R.id.detailsPoster).setImageResource(
                this.resources.getIdentifier(
                    it.img,
                    "id",
                    this.packageName
                )
            )
        }
    }
}


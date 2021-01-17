package com.example.otusandroidbasic

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.movieButton1).setOnClickListener {
            Intent(this, MovieDescription::class.java).apply {
                val data = MovieData(
                    "@string/darkKnightTitle",
                    "@drawable/batman6_23s",
                    "@string/darkKnightDescription"
                )
                putExtra(MovieDescription.MOVIE_DATA, data)
                startActivity(this)
            }
        }
    }
}
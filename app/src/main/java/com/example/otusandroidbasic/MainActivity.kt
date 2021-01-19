package com.example.otusandroidbasic

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"

    companion object {
        const val SELECTED_TITLE = "SELECTED_TITLE"
        const val SELECTED_TITLE_COLOR = "#B33C3C"
        const val DEFAULT_TITLE_COLOR = "#323232"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(tag, "created")

        fun setTextColor(elementId: String, color: String) {
            val id = this.resources.getIdentifier(elementId, "id", this.packageName)
            findViewById<TextView>(id)?.setTextColor(Color.parseColor(color))
        }

        intent.getStringExtra(SELECTED_TITLE)?.let {
            Log.i(tag, "found selected title: $it")
            setTextColor(it, SELECTED_TITLE_COLOR)
        }

        fun clickDetail(data: MovieData, titleId: String) {
            Log.i(tag, "click on movie ${data.title}")
            // снимаем выделение с предыдущего выбранного фильма, если был
            intent.getStringExtra(SELECTED_TITLE)?.let {
                Log.i(tag, "disable selected title: $it")
                setTextColor(it, DEFAULT_TITLE_COLOR)
            }
            setTextColor(titleId, SELECTED_TITLE_COLOR)
            intent.putExtra(SELECTED_TITLE, titleId)
            Intent(this, MovieDescription::class.java).apply {
                putExtra(MovieDescription.MOVIE_DATA, data)
                startActivity(this)
            }
        }

        findViewById<View>(R.id.movieButton1).setOnClickListener {
            clickDetail(
                MovieData(
                    "@string/darkKnightTitle",
                    "@drawable/batman6_23s",
                    "@string/darkKnightDescription"
                ), "@posterTitle1"
            )
        }
        findViewById<View>(R.id.movieButton2).setOnClickListener {
            clickDetail(
                MovieData(
                    "@string/quietPlaceTitle",
                    "@drawable/quietplace2_10s",
                    "@string/quietPlaceDescription"
                ), "@posterTitle2"
            )
        }
        findViewById<View>(R.id.movieButton3).setOnClickListener {
            clickDetail(
                MovieData(
                    "@string/furious9Title",
                    "@drawable/furious9_12s",
                    "@string/furious9Description"
                ), "@posterTitle3"
            )
        }
    }
}
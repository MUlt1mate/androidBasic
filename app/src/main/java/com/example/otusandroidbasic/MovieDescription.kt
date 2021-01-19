package com.example.otusandroidbasic

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ShareActionProvider
import android.widget.TextView

class MovieDescription : AppCompatActivity() {
    private val tag = "MovieDescription"

    companion object {
        const val MOVIE_DATA = "MOVIE_DATA"
        const val FEEDBACK_DATA = "FEEDBACK_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        Log.i(tag, "created")

        findViewById<View>(R.id.shareBtn).setOnClickListener {
            Log.i(tag, "clicked share")
            val intent = Intent(this, ShareActionProvider::class.java)
            startActivity(intent)
        }

        intent.getParcelableExtra<MovieData>(MOVIE_DATA)?.let {
            Log.i(tag, "received movie data ${it.title}")
            fun getResourceById(name: String) =
                this.resources.getIdentifier(name, "id", this.packageName)

            findViewById<TextView>(R.id.movieTitle).setText(getResourceById(it.title))
            findViewById<TextView>(R.id.movieDescription).setText(getResourceById(it.description))
            findViewById<ImageView>(R.id.detailsPoster).setImageResource(getResourceById(it.img))
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK, Intent().apply {
            Log.i(tag, "setting result")
            putExtra(
                FEEDBACK_DATA, FeedbackData(
                    findViewById<TextView>(R.id.commentText).text.toString(),
                    findViewById<CheckBox>(R.id.likeCheckbox).isChecked
                )
            )
        })
        super.onBackPressed()
    }
}


package com.example.otusandroidbasic

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class MovieDescription : AppCompatActivity() {
    private val tag = "MovieDescription"

    companion object {
        const val MOVIE_DATA = "MOVIE_DATA"
        const val FEEDBACK_DATA = "FEEDBACK_DATA"
    }

    // ищет ресурс по имени
    private fun getResourceById(name: String) =
        this.resources.getIdentifier(name, "id", this.packageName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        Log.i(tag, "created")

        val it = intent.getParcelableExtra<MovieData>(MOVIE_DATA)
        if (it == null) {
            Log.w(tag, "movie data not found")
            return
        }
        Log.i(tag, "received movie data ${it.title}")

        val title = getResourceById(it.title)
        findViewById<TextView>(R.id.movieTitle).setText(title)
        findViewById<TextView>(R.id.movieDescription).setText(getResourceById(it.description))
        findViewById<ImageView>(R.id.detailsPoster).setImageResource(getResourceById(it.img))

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


package com.example.otusandroidbasic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MovieDescription : AppCompatActivity() {
    private val tag = "MovieDescription"

    companion object {
        const val MOVIE_DATA = "MOVIE_DATA"
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

//        val title = getResourceById(it.title)
//        findViewById<TextView>(R.id.movieTitle).setText(title)
//        findViewById<TextView>(R.id.movieDescription).setText(getResourceById(it.description))
//        findViewById<ImageView>(R.id.detailsPoster).setImageResource(getResourceById(it.img))

        /*findViewById<View>(R.id.shareBtn).setOnClickListener {
            Log.i(tag, "clicked share")
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Check out ${getString(title)} in my app")
            }

            startActivity(Intent.createChooser(sendIntent, null))
        }*/
    }
}


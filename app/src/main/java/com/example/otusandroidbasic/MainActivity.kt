package com.example.otusandroidbasic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.movieList) }
/*
    companion object {
        const val SELECTED_TITLE = "SELECTED_TITLE"
        const val SELECTED_TITLE_COLOR = "#B33C3C"
        const val DEFAULT_TITLE_COLOR = "#323232"
    }

    // устанавливает цвет TextView
    private fun setTextColor(elementId: String, color: String) {
        val id = this.resources.getIdentifier(elementId, "id", this.packageName)
        findViewById<TextView>(id)?.setTextColor(Color.parseColor(color))
    }

    // обработка выбора фильма из списка
    private fun showDetail(data: MovieData, titleId: String) {
        Log.i(tag, "click on movie ${data.title}")
        // снимаем выделение с предыдущего выбранного фильма, если был
        intent.getStringExtra(SELECTED_TITLE)?.let {
            Log.i(tag, "disable selected title: $it")
            setTextColor(it, DEFAULT_TITLE_COLOR)
        }
        // выделяем и запоминаем новый фильм
        setTextColor(titleId, SELECTED_TITLE_COLOR)
        intent.putExtra(SELECTED_TITLE, titleId)
        // открываем экран с подробной информацией
        Intent(this, MovieDescription::class.java).apply {
            putExtra(MovieDescription.MOVIE_DATA, data)
            startActivity(this)
        }
    }
*/

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MovieAdapter(MovieRepository().getAll())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(tag, "created")

        initRecycler()

        /*intent.getStringExtra(SELECTED_TITLE)?.let {
            Log.i(tag, "found selected title: $it")
            setTextColor(it, SELECTED_TITLE_COLOR)
        }

        findViewById<View>(R.id.movieButton1).setOnClickListener {
            showDetail(
                MovieData(
                    "@string/darkKnightTitle",
                    "@drawable/batman6_23s",
                    "@string/darkKnightDescription"
                ), "@posterTitle1"
            )
        }
        findViewById<View>(R.id.movieButton2).setOnClickListener {
            showDetail(
                MovieData(
                    "@string/quietPlaceTitle",
                    "@drawable/quietplace2_10s",
                    "@string/quietPlaceDescription"
                ), "@posterTitle2"
            )
        }
        findViewById<View>(R.id.movieButton3).setOnClickListener {
            showDetail(
                MovieData(
                    "@string/furious9Title",
                    "@drawable/furious9_12s",
                    "@string/furious9Description"
                ), "@posterTitle3"
            )
        }*/
    }
}
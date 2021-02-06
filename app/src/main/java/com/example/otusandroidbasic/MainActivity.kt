package com.example.otusandroidbasic

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.movieList) }

    companion object {
        const val SELECTED_TITLE = "SELECTED_TITLE"
        const val SELECTED_TITLE_COLOR = "#B33C3C"
        const val DEFAULT_TITLE_COLOR = "#323232"
    }

    // устанавливает цвет TextView
    private fun setTextColor(elementId: Int, color: String) {
        findViewById<TextView>(elementId)?.setTextColor(Color.parseColor(color))
    }

    // обработка выбора фильма из списка
    private fun showDetail(data: MovieData) {
        Log.i(tag, "click on movie ${data.title}")

//        changeLastSelectedMovie(data)
        // открываем экран с подробной информацией
        Intent(this, MovieDescription::class.java).apply {
            putExtra(MovieDescription.MOVIE_DATA, data)
            startActivity(this)
        }
    }

    private fun changeLastSelectedMovie(data: MovieData) {
        // снимаем выделение с предыдущего выбранного фильма, если был
        intent.getIntExtra(SELECTED_TITLE, 0)?.let {
            Log.i(tag, "disable selected title: $it")
            setTextColor(it, DEFAULT_TITLE_COLOR)
        }
        // выделяем и запоминаем новый фильм
        setTextColor(data.title, SELECTED_TITLE_COLOR)
        intent.putExtra(SELECTED_TITLE, data.title)
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter =
            MovieAdapter(MovieRepository().getAll(), object : MovieAdapter.DetailsClickListener {
                override fun onDetailsClick(movieItem: MovieData) = showDetail(movieItem)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(tag, "created")

        initRecycler()

//        markLastSelectedMovie()
    }

    private fun markLastSelectedMovie() {
        intent.getIntExtra(SELECTED_TITLE, 0)?.let {
            Log.i(tag, "found selected title: $it")
            setTextColor(it, SELECTED_TITLE_COLOR)
        }
    }
}
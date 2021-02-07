package com.example.otusandroidbasic

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.movieList) }
    private val movieRepository = getRepository()

    private fun initRecycler() {
        val orientation = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LinearLayoutManager.HORIZONTAL
            else -> LinearLayoutManager.VERTICAL
        }
        val layoutManager = LinearLayoutManager(this, orientation, false)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter =
            MovieAdapter(
                movieRepository,
                object : MovieAdapter.DetailsClickListener {
                    override fun onDetailsClick(movieItem: MovieData) =
                        showDetail(this@MainActivity, movieItem)

                    override fun onFavoriteClick(
                        movieItem: MovieData,
                        added: Boolean,
                        position: Int
                    ) {
                        if (added) {
                            movieRepository.addToFavorite(movieItem.title)
                        } else {
                            movieRepository.removeFromFavorite(movieItem.title)
                        }
                        recyclerView.adapter?.notifyItemChanged(position)
                    }
                })
    }

    override fun onBackPressed() {
        val bld: AlertDialog.Builder = AlertDialog.Builder(this)
            .setMessage(R.string.exitQuestion)
            .setTitle(R.string.confirmation)
            .setPositiveButton(R.string.yes) { _, _ -> super.onBackPressed() }
            .setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }

        bld.create().show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()

        findViewById<View>(R.id.openFavorites).setOnClickListener {
            Intent(this, Favorites::class.java).apply {
                startActivityForResult(this, 0)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i(tag, "refreshing list")
        recyclerView.adapter?.notifyDataSetChanged()
    }
}
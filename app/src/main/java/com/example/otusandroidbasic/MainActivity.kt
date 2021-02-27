package com.example.otusandroidbasic

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            return
        }
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

        openMovieList()
    }

    private fun openMovieList() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, MovieListFragment(), MovieListFragment.TAG)
            .commit()
    }

    fun openFavoriteList() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                FavoriteMovieListFragment(),
                FavoriteMovieListFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    fun onDetailsClick(movieItem: MovieData) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieDescriptionFragment.newInstance(movieItem),
                MovieDescriptionFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }
}
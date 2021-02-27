package com.example.otusandroidbasic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class MovieDescriptionFragment : Fragment() {
    companion object {
        const val TAG = "MovieDescriptionFragment"
        const val MOVIE_DATA = "MOVIE_DATA"

        fun newInstance(data: MovieData): MovieDescriptionFragment {
            return MovieDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_DATA, data)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val it = arguments?.getParcelable<MovieData>(MOVIE_DATA)
        if (it == null) {
            Log.w(tag, "movie data not found")
            return
        }
        val title = it.title
        Log.i(tag, "received movie data $title")

        view.findViewById<TextView>(R.id.movieTitle).setText(title)
        view.findViewById<TextView>(R.id.movieDescription).setText(it.description)
        view.findViewById<ImageView>(R.id.detailsPoster).setImageResource(it.img)

        view.findViewById<View>(R.id.shareBtn).setOnClickListener {
            Log.i(tag, "clicked share")
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Check out ${getString(title)} in my app")
            }

            startActivity(Intent.createChooser(sendIntent, null))
        }
    }
}
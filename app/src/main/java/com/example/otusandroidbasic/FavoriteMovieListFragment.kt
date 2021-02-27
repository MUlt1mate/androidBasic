package com.example.otusandroidbasic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class FavoriteMovieListFragment : Fragment() {
    companion object {
        const val TAG = "FavoriteMovieListFragment"
    }

    private val movieRepository = getRepository()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.movieList)
        recyclerView.adapter = FavoriteMovieAdapter(
            movieRepository,
            detailsClickListener(recyclerView)
        )
    }

    private fun detailsClickListener(recyclerView: RecyclerView): MovieAdapter.DetailsClickListener {
        return object : MovieAdapter.DetailsClickListener {
            override fun onDetailsClick(movieItem: MovieData) =
                (activity as MainActivity).onDetailsClick(movieItem)

            override fun onFavoriteClick(
                movieItem: MovieData,
                added: Boolean,
                position: Int
            ) {
                movieRepository.removeFromFavorite(movieItem.title)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
}
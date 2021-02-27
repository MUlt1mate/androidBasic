package com.example.otusandroidbasic

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MovieListFragment : Fragment() {
    companion object {
        const val TAG = "MovieListFragment"
    }

    private val movieRepository = getRepository()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.movieList)
        recyclerView.adapter = MovieAdapter(
            movieRepository,
            detailsClickListener(recyclerView)
        )
        addDecorations(
            recyclerView,
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        )
        view.findViewById<View>(R.id.openFavorites).setOnClickListener {
            (activity as MainActivity).openFavoriteList()
        }
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
                if (added) {
                    movieRepository.addToFavorite(movieItem.title)
                } else {
                    movieRepository.removeFromFavorite(movieItem.title)
                }
                recyclerView.adapter?.notifyItemChanged(position)
            }
        }
    }

    private fun addDecorations(recyclerView: RecyclerView, isVertical: Boolean) {
        val orientation: Int = if (isVertical) {
            DividerItemDecoration.VERTICAL
        } else {
            DividerItemDecoration.HORIZONTAL
        }

        val itemDecoration = DividerItemDecoration(requireContext(), orientation)
        itemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.divider_list
            )!!
        )
        recyclerView.addItemDecoration(itemDecoration)
    }

}
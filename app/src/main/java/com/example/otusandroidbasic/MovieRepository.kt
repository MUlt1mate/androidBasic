package com.example.otusandroidbasic

class MovieRepository {
    private var favList = mutableSetOf<Int>()
    private val movieList = listOf(
        MovieData(
            R.string.darkKnightTitle,
            R.drawable.batman6_23s,
            R.string.darkKnightDescription
        ),
        MovieData(
            R.string.quietPlaceTitle,
            R.drawable.quietplace2_10s,
            R.string.quietPlaceDescription
        ),
        MovieData(
            R.string.furious9Title,
            R.drawable.furious9_12s,
            R.string.furious9Description
        )
    )

    fun getAll(): List<MovieData> = movieList
    fun getByTitle(titleRes: Int): MovieData = movieList.first { it.title == titleRes }
    fun addToFavorite(movie: Int) {
        favList.add(movie)
    }

    fun getFavorite(): Set<Int> = favList
    fun setFavorite(set: Set<Int>) {
        favList = set.toMutableSet()
    }
}
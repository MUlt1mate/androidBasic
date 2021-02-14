package com.example.otusandroidbasic

interface IMovieRepository {
    fun getAll(): MutableList<MovieData>
    fun addToFavorite(movie: Int)
    fun removeFromFavorite(movie: Int)
    fun getFavorite(): Set<Int>
    fun setFavorite(set: Set<Int>)
}

private val repo by lazy { MovieRepository() }

fun getRepository(): IMovieRepository {
    return repo
}

private class MovieRepository : IMovieRepository {
    private var favList = mutableSetOf<Int>()
    private val movieList = mutableListOf(
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
        ),
        MovieData(
            R.string.tenetTitle,
            R.drawable.tenet_6s,
            R.string.tenetDescription
        )
    )

    override fun getAll(): MutableList<MovieData> = movieList
    override fun addToFavorite(movie: Int) {
        favList.add(movie)
    }
    override fun removeFromFavorite(movie: Int) {
        favList.remove(movie)
    }

    override fun getFavorite(): Set<Int> = favList
    override fun setFavorite(set: Set<Int>) {
        favList = set.toMutableSet()
    }
}
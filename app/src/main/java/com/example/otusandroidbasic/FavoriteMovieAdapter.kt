package com.example.otusandroidbasic

class FavoriteMovieAdapter(
    movieRepository: IMovieRepository,
    clickListener: DetailsClickListener
) : MovieAdapter(movieRepository, clickListener) {
    override fun getItemCount() = movieRepository.getFavorite().size

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val favoriteMovies = movieRepository.getFavorite()
        val items = movieRepository.getAll().filter { favoriteMovies.contains(it.title) }
        bindVH(holder, items[position], true, position)
    }

}
package id.rosyid.moviecatalogue.data.repository

import id.rosyid.moviecatalogue.data.entities.Movie
import id.rosyid.moviecatalogue.data.entities.MovieDetail
import id.rosyid.moviecatalogue.data.remote.datasource.MoviesDataSource
import kotlinx.coroutines.runBlocking

class FakeMoviesRepository(
    private val moviesDataSource: MoviesDataSource
) {
    fun getAllMovies(): List<Movie> = runBlocking {
        val movieResponse = moviesDataSource.getAllMovies()
        val movieList: MutableList<Movie> = mutableListOf()
        movieResponse.data?.results?.forEach {
            movieList.add(it)
        }
        return@runBlocking movieList
    }

    fun getMovieDetailById(id: Int): MovieDetail? = runBlocking {
        val movieResponse = moviesDataSource.getMovieDetailById(id)
        return@runBlocking movieResponse.data
    }
}

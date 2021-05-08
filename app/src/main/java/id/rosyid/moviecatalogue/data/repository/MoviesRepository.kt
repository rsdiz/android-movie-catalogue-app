package id.rosyid.moviecatalogue.data.repository

import id.rosyid.moviecatalogue.data.local.IMovieDetailDao
import id.rosyid.moviecatalogue.data.local.IMoviesDao
import id.rosyid.moviecatalogue.data.remote.datasource.MoviesDataSource
import id.rosyid.moviecatalogue.utils.performGetOperation
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemote: MoviesDataSource,
    private val moviesLocal: IMoviesDao,
    private val moviesDetailLocal: IMovieDetailDao
) {
    fun getAllMovies() = performGetOperation(
        databaseQuery = { moviesLocal.getAllMovies() },
        networkCall = { moviesRemote.getAllMovies() },
        saveCallResult = { moviesLocal.insertAll(it.results) }
    )

    fun getMovieDetailById(id: Int) = performGetOperation(
        databaseQuery = { moviesDetailLocal.getDetail(id) },
        networkCall = { moviesRemote.getMovieDetailById(id) },
        saveCallResult = { moviesDetailLocal.insert(it) }
    )
}

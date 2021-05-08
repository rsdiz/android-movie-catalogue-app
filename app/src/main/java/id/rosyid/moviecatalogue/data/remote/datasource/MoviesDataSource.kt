package id.rosyid.moviecatalogue.data.remote.datasource

import id.rosyid.moviecatalogue.data.remote.BaseDataSource
import id.rosyid.moviecatalogue.data.remote.api.TMdbService
import javax.inject.Inject

class MoviesDataSource @Inject constructor(
    private val tMdbService: TMdbService
) : BaseDataSource() {
    suspend fun getAllMovies() = getResult { tMdbService.getMovies() }
    suspend fun getMovieDetailById(id: Int) = getResult { tMdbService.getMovieDetailById(id) }
}

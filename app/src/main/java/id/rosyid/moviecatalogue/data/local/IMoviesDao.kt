package id.rosyid.moviecatalogue.data.local

import androidx.room.*
import id.rosyid.moviecatalogue.data.entities.Movie
import id.rosyid.moviecatalogue.data.local.base.IBaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface IMoviesDao : IBaseDao<Movie> {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("DELETE FROM movies")
    fun deleteAll()
}

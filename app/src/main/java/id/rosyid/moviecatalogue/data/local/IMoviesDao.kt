package id.rosyid.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import id.rosyid.moviecatalogue.data.entities.Movie
import id.rosyid.moviecatalogue.data.local.base.IBaseDao

@Dao
interface IMoviesDao : IBaseDao<Movie> {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movies")
    fun deleteAll()
}

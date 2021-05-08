package id.rosyid.moviecatalogue.data.local

import androidx.room.*
import id.rosyid.moviecatalogue.data.entities.Movies
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listMovies: List<Movies>)

    @Query("DELETE FROM movies")
    fun deleteAll()
}

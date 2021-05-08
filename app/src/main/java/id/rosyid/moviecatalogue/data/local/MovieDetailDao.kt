package id.rosyid.moviecatalogue.data.local

import androidx.room.*
import id.rosyid.moviecatalogue.data.entities.MovieDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM movie_detail WHERE id = :id")
    fun getDetail(id: Int): Flow<MovieDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movieDetail: MovieDetail)

    @Query("DELETE FROM movie_detail")
    fun deleteAll()
}

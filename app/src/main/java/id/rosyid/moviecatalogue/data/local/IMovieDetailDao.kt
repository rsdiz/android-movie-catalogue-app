package id.rosyid.moviecatalogue.data.local

import androidx.room.*
import id.rosyid.moviecatalogue.data.entities.MovieDetail
import id.rosyid.moviecatalogue.data.local.base.IBaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface IMovieDetailDao : IBaseDao<MovieDetail> {
    @Query("SELECT * FROM movie_detail WHERE id = :id")
    fun getDetail(id: Int): Flow<MovieDetail>

    @Query("DELETE FROM movie_detail")
    fun deleteAll()
}

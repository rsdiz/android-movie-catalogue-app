package id.rosyid.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import id.rosyid.moviecatalogue.data.entities.MovieDetail
import id.rosyid.moviecatalogue.data.local.base.IBaseDao

@Dao
interface IMovieDetailDao : IBaseDao<MovieDetail> {
    @Query("SELECT * FROM movie_detail WHERE id = :id")
    fun getDetail(id: Int): LiveData<MovieDetail>

    @Query("DELETE FROM movie_detail")
    fun deleteAll()
}

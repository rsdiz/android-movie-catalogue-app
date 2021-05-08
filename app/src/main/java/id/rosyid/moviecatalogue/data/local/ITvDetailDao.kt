package id.rosyid.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.rosyid.moviecatalogue.data.entities.TvDetail
import id.rosyid.moviecatalogue.data.local.base.IBaseDao

@Dao
interface ITvDetailDao : IBaseDao<TvDetail> {
    @Query("SELECT * FROM tv_detail WHERE id = :id")
    fun getDetailById(id: Int): LiveData<TvDetail>

    @Query("DELETE FROM tv_detail")
    fun deleteAll()
}

package id.rosyid.moviecatalogue.data.local

import androidx.room.Dao
import androidx.room.Query
import id.rosyid.moviecatalogue.data.entities.TvDetail
import id.rosyid.moviecatalogue.data.local.base.IBaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface ITvDetailDao : IBaseDao<TvDetail> {
    @Query("SELECT * FROM tv_detail WHERE id = :id")
    fun getDetailById(id: Int): Flow<TvDetail>

    @Query("DELETE FROM tv_detail")
    fun deleteAll()
}

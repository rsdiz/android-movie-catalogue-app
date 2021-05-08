package id.rosyid.moviecatalogue.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.rosyid.moviecatalogue.data.entities.TvDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDetailDao {
    @Query("SELECT * FROM tv_detail WHERE id = :id")
    fun getDetailById(id: Int): Flow<TvDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvDetail(tvDetail: TvDetail)

    @Query("DELETE FROM tv_detail")
    fun deleteAll()
}

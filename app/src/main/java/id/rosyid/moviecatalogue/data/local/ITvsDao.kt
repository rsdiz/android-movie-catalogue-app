package id.rosyid.moviecatalogue.data.local

import androidx.room.*
import id.rosyid.moviecatalogue.data.entities.Tv
import id.rosyid.moviecatalogue.data.local.base.IBaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface ITvsDao : IBaseDao<Tv> {
    @Query("SELECT * FROM tvs")
    fun getAllTvs(): Flow<List<Tv>>

    @Query("DELETE FROM tvs")
    fun deleteAll()
}

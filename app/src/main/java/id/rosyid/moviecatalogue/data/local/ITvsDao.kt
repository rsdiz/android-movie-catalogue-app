package id.rosyid.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import id.rosyid.moviecatalogue.data.entities.Tv
import id.rosyid.moviecatalogue.data.local.base.IBaseDao

@Dao
interface ITvsDao : IBaseDao<Tv> {
    @Query("SELECT * FROM tvs")
    fun getAllTvs(): LiveData<List<Tv>>

    @Query("DELETE FROM tvs")
    fun deleteAll()
}

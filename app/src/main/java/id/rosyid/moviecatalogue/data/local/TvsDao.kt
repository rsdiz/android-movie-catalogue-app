package id.rosyid.moviecatalogue.data.local

import androidx.room.*
import id.rosyid.moviecatalogue.data.entities.Tvs
import kotlinx.coroutines.flow.Flow

@Dao
interface TvsDao {
    @Query("SELECT * FROM tvs")
    fun getAllTvs(): Flow<List<Tvs>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listTvs: List<Tvs>)

    @Query("DELETE FROM tvs")
    fun deleteAll()
}

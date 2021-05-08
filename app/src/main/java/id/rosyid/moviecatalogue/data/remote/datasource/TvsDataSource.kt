package id.rosyid.moviecatalogue.data.remote.datasource

import id.rosyid.moviecatalogue.data.remote.BaseDataSource
import id.rosyid.moviecatalogue.data.remote.api.TMdbService
import javax.inject.Inject

class TvsDataSource @Inject constructor(
    private val tMdbService: TMdbService
) : BaseDataSource() {
    suspend fun getAllTvs() = getResult { tMdbService.getTvs() }
    suspend fun getTvDetailById(id: Int) = getResult { tMdbService.getTvDetailById(id) }
}

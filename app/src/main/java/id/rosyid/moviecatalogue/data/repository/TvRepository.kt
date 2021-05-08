package id.rosyid.moviecatalogue.data.repository

import id.rosyid.moviecatalogue.data.local.ITvDetailDao
import id.rosyid.moviecatalogue.data.local.ITvsDao
import id.rosyid.moviecatalogue.data.remote.datasource.TvsDataSource
import id.rosyid.moviecatalogue.utils.performGetOperation
import javax.inject.Inject

class TvRepository @Inject constructor(
    private val tvsRemote: TvsDataSource,
    private val tvsLocal: ITvsDao,
    private val tvDetailLocal: ITvDetailDao
) {
    fun getAllTvs() = performGetOperation(
        databaseQuery = { tvsLocal.getAllTvs() },
        networkCall = { tvsRemote.getAllTvs() },
        saveCallResult = { tvsLocal.insertAll(it.results) }
    )

    fun getTvDetailById(id: Int) = performGetOperation(
        databaseQuery = { tvDetailLocal.getDetailById(id) },
        networkCall = { tvsRemote.getTvDetailById(id) },
        saveCallResult = { tvDetailLocal.insert(it) }
    )
}

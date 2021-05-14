package id.rosyid.moviecatalogue.data.repository

import id.rosyid.moviecatalogue.data.entities.Tv
import id.rosyid.moviecatalogue.data.remote.datasource.TvsDataSource
import kotlinx.coroutines.runBlocking

class FakeTvsRepository(
    private val tvsDataSource: TvsDataSource
) {
    fun getAllTvs(): List<Tv> = runBlocking {
        val tvsResponse = tvsDataSource.getAllTvs()
        val tvList = mutableListOf<Tv>()
        tvsResponse.data?.results?.forEach {
            tvList.add(it)
        }
        return@runBlocking tvList
    }

    fun getTvDetailById(id: Int) = runBlocking {
        val tvResponse = tvsDataSource.getTvDetailById(id)
        return@runBlocking tvResponse.data
    }
}

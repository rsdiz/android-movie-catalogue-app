package id.rosyid.moviecatalogue.data.repository

import com.nhaarman.mockitokotlin2.verify
import id.rosyid.moviecatalogue.data.remote.datasource.TvsDataSource
import id.rosyid.moviecatalogue.utils.DataDummy
import id.rosyid.moviecatalogue.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class TvsRepositoryTest {
    private val remote = Mockito.mock(TvsDataSource::class.java)
    private val tvsRepository = FakeTvsRepository(remote)

    private val tvResponse = DataDummy.generateTvs()
    private val tvDetailResponse = DataDummy.getTvDetail()

    @Test
    fun getAllTvs() = runBlocking {
        Mockito.`when`(remote.getAllTvs())
            .thenReturn(Resource(Resource.Status.SUCCESS, tvResponse, ""))
        val tvsEntities = tvsRepository.getAllTvs()
        verify(remote).getAllTvs()
        Assert.assertNotNull(tvsEntities)
        Assert.assertEquals(tvResponse.results.size.toLong(), tvsEntities.size.toLong())
    }

    @Test
    fun getTvDetailById() = runBlocking {
        Mockito.`when`(remote.getTvDetailById(tvDetailResponse.id))
            .thenReturn(Resource(Resource.Status.SUCCESS, tvDetailResponse, ""))
        val tvEntities = tvsRepository.getTvDetailById(tvDetailResponse.id)
        verify(remote).getTvDetailById(tvDetailResponse.id)
        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(tvDetailResponse, tvEntities)
    }
}

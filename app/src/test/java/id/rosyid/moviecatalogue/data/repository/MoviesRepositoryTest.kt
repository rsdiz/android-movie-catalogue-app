package id.rosyid.moviecatalogue.data.repository

import com.nhaarman.mockitokotlin2.verify
import id.rosyid.moviecatalogue.data.remote.datasource.MoviesDataSource
import id.rosyid.moviecatalogue.utils.DataDummy
import id.rosyid.moviecatalogue.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class MoviesRepositoryTest {
    private val remote = Mockito.mock(MoviesDataSource::class.java)
    private val movieRepository = FakeMoviesRepository(remote)

    private val moviesResponse = DataDummy.generateMovies()
    private val movieDetailResponse = DataDummy.getMovieDetail()

    @Test
    fun getAllMovies() = runBlocking {
        Mockito.`when`(remote.getAllMovies())
            .thenReturn(Resource(Resource.Status.SUCCESS, moviesResponse, ""))
        val moviesEntities = movieRepository.getAllMovies()
        verify(remote).getAllMovies()
        Assert.assertNotNull(moviesEntities)
        Assert.assertEquals(moviesResponse.results.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getMovieDetailById() = runBlocking {
        Mockito.`when`(remote.getMovieDetailById(movieDetailResponse.id)).thenReturn(
            Resource(Resource.Status.SUCCESS, movieDetailResponse, "")
        )
        val movieEntities = movieRepository.getMovieDetailById(movieDetailResponse.id)
        verify(remote).getMovieDetailById(movieDetailResponse.id)
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(movieDetailResponse, movieEntities)
    }
}

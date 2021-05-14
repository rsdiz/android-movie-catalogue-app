package id.rosyid.moviecatalogue.ui.homepage.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.rosyid.moviecatalogue.data.entities.Movie
import id.rosyid.moviecatalogue.data.repository.MoviesRepository
import id.rosyid.moviecatalogue.utils.DataDummy
import id.rosyid.moviecatalogue.utils.Resource
import id.rosyid.moviecatalogue.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<Movie>>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getListMovies() = testCoroutineRule.runBlockingTest {
        val resource = Resource.success(DataDummy.generateListMovie())
        val data = MutableLiveData<Resource<List<Movie>>>().apply { value = resource }
        `when`(moviesRepository.getAllMovies()).thenReturn(data)

        viewModel.setUp()
        viewModel.listMovies.observeForever(observer)
        verify(moviesRepository).getAllMovies()
        verify(observer).onChanged(resource)
        viewModel.listMovies.removeObserver(observer)

        val movieEntities = viewModel.listMovies.value
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(4, movieEntities?.data?.size)
    }
}

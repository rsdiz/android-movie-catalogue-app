package id.rosyid.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.rosyid.moviecatalogue.data.entities.MovieDetail
import id.rosyid.moviecatalogue.data.entities.TvDetail
import id.rosyid.moviecatalogue.data.repository.MoviesRepository
import id.rosyid.moviecatalogue.data.repository.TvRepository
import id.rosyid.moviecatalogue.utils.DataDummy
import id.rosyid.moviecatalogue.utils.Resource
import id.rosyid.moviecatalogue.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var tvsRepository: TvRepository

    @Mock
    private lateinit var observerTvDetail: Observer<Resource<TvDetail>>

    @Mock
    private lateinit var observerMovieDetail: Observer<Resource<MovieDetail>>

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(tvsRepository, moviesRepository)
    }

    @Test
    fun getTvDetail() = testCoroutineRule.runBlockingTest {
        val idTv = DataDummy.getTvDetail().id
        val resource = Resource.success(DataDummy.getTvDetail())
        val data = MutableLiveData<Resource<TvDetail>>().apply { value = resource }
        `when`(tvsRepository.getTvDetailById(idTv)).thenReturn(data)

        viewModel.initDetail(idTv, DetailActivity.TYPE_TVSERIES)
        viewModel.tvDetail.observeForever(observerTvDetail)
        verify(tvsRepository).getTvDetailById(idTv)
        verify(observerTvDetail).onChanged(resource)
        viewModel.tvDetail.removeObserver(observerTvDetail)

        val tvDetail = viewModel.tvDetail.value
        Assert.assertNotNull(tvDetail)
        Assert.assertEquals(resource.data, tvDetail?.data)
    }

    @Test
    fun getMovieDetail() = testCoroutineRule.runBlockingTest {
        val idMovie = DataDummy.getMovieDetail().id
        val resource = Resource.success(DataDummy.getMovieDetail())
        val data = MutableLiveData<Resource<MovieDetail>>().apply { value = resource }
        `when`(moviesRepository.getMovieDetailById(idMovie)).thenReturn(data)

        viewModel.initDetail(idMovie, DetailActivity.TYPE_MOVIES)
        viewModel.movieDetail.observeForever(observerMovieDetail)
        verify(moviesRepository).getMovieDetailById(idMovie)
        verify(observerMovieDetail).onChanged(resource)
        viewModel.movieDetail.removeObserver(observerMovieDetail)

        val movieDetail = viewModel.movieDetail.value
        Assert.assertNotNull(movieDetail)
        Assert.assertEquals(resource.data, movieDetail?.data)
    }
}

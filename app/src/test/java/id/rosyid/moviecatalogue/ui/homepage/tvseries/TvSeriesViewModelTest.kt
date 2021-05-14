package id.rosyid.moviecatalogue.ui.homepage.tvseries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.rosyid.moviecatalogue.data.entities.Tv
import id.rosyid.moviecatalogue.data.repository.TvRepository
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
class TvSeriesViewModelTest {
    private lateinit var viewModel: TvSeriesViewModel

    @Mock
    private lateinit var tvRepository: TvRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<Tv>>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        viewModel = TvSeriesViewModel(tvRepository)
    }

    @Test
    fun getListTvs() = testCoroutineRule.runBlockingTest {
        val resource = Resource.success(DataDummy.generateListTv())
        val data = MutableLiveData<Resource<List<Tv>>>().apply { value = resource }
        `when`(tvRepository.getAllTvs()).thenReturn(data)

        viewModel.setUp()
        viewModel.listTvs.observeForever(observer)
        verify(tvRepository).getAllTvs()
        verify(observer).onChanged(resource)
        viewModel.listTvs.removeObserver(observer)

        val tvEntities = viewModel.listTvs.value
        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(4, tvEntities?.data?.size)
    }
}

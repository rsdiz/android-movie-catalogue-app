package id.rosyid.moviecatalogue.ui.homepage.tvseries

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvSeriesViewModelTest {
    private var tvSeriesViewModel: TvSeriesViewModel? = null
    private val totalTvSeries = 10

    @Before
    fun setUp() {
        tvSeriesViewModel = TvSeriesViewModel()
    }

    @Test
    fun getTvSeries() {
        val tvSeriesList = tvSeriesViewModel?.getTvSeries()
        assertNotNull(tvSeriesList)
        assertEquals(totalTvSeries, tvSeriesList?.size)
    }

    @After
    fun finish() {
        tvSeriesViewModel = null
    }
}

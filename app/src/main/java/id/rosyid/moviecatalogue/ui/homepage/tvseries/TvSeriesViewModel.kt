package id.rosyid.moviecatalogue.ui.homepage.tvseries

import androidx.lifecycle.ViewModel
import id.rosyid.moviecatalogue.utils.TvSeriesData

class TvSeriesViewModel : ViewModel() {
    fun getTvSeries() = TvSeriesData.generateTvSeries()
}

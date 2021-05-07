package id.rosyid.moviecatalogue.ui.homepage.tvseries

import androidx.lifecycle.ViewModel
import id.rosyid.moviecatalogue.data.TvEntity

class TvSeriesViewModel : ViewModel() {
    fun getTvSeries() = listOf<TvEntity>()
}

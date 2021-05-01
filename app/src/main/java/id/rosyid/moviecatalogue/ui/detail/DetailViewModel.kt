package id.rosyid.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import id.rosyid.moviecatalogue.data.BaseEntity
import id.rosyid.moviecatalogue.utils.MoviesData
import id.rosyid.moviecatalogue.utils.TvSeriesData

class DetailViewModel : ViewModel() {
    private var id = -1
    private lateinit var type: String

    fun setSelectedId(id: Int, type: String) {
        this.id = id
        this.type = type
    }

    fun getItemDetail(): BaseEntity {
        lateinit var itemDetail: BaseEntity
        if (type == DetailActivity.TYPE_MOVIES)
            itemDetail = MoviesData.getMovie(id)
        else if (type == DetailActivity.TYPE_TVSERIES)
            itemDetail = TvSeriesData.getTvSeries(id)

        return itemDetail
    }
}

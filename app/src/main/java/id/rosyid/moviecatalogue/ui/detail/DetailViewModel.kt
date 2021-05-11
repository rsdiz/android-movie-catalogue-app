package id.rosyid.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rosyid.moviecatalogue.data.entities.MovieDetail
import id.rosyid.moviecatalogue.data.entities.TvDetail
import id.rosyid.moviecatalogue.data.repository.MoviesRepository
import id.rosyid.moviecatalogue.data.repository.TvRepository
import id.rosyid.moviecatalogue.ui.detail.DetailActivity.Companion.TYPE_MOVIES
import id.rosyid.moviecatalogue.ui.detail.DetailActivity.Companion.TYPE_TVSERIES
import id.rosyid.moviecatalogue.utils.Resource
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val tvRepository: TvRepository,
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private var id: Int = -1

    private lateinit var _tvDetail: MutableLiveData<Resource<TvDetail>>
    val tvDetail: LiveData<Resource<TvDetail>>
        get() = _tvDetail

    private lateinit var _movieDetail: MutableLiveData<Resource<MovieDetail>>
    val movieDetail: LiveData<Resource<MovieDetail>>
        get() = _movieDetail

    fun initDetail(id: Int, type: String) {
        this.id = id
        when (type) {
            TYPE_MOVIES -> {
                _movieDetail = moviesRepository.getMovieDetailById(id) as MutableLiveData<Resource<MovieDetail>>
            }
            TYPE_TVSERIES -> {
                _tvDetail = tvRepository.getTvDetailById(id) as MutableLiveData<Resource<TvDetail>>
            }
        }
    }
}

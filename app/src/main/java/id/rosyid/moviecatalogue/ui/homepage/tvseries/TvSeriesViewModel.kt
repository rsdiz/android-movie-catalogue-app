package id.rosyid.moviecatalogue.ui.homepage.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rosyid.moviecatalogue.data.entities.Tv
import id.rosyid.moviecatalogue.data.repository.TvRepository
import id.rosyid.moviecatalogue.utils.Resource
import javax.inject.Inject

@HiltViewModel
class TvSeriesViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {
    private var _listTvs = MutableLiveData<Resource<List<Tv>>>()
    val listTvs: LiveData<Resource<List<Tv>>>
        get() = _listTvs

    fun setUp() {
        _listTvs = tvRepository.getAllTvs() as MutableLiveData<Resource<List<Tv>>>
    }
}

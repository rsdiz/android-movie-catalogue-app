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
    private val _listTvs = MutableLiveData<Resource<List<Tv>>>()
    val listTvs: LiveData<Resource<List<Tv>>> = _listTvs

    private fun getTvs() {
        _listTvs.value = tvRepository.getAllTvs().value
    }

    init {
        getTvs()
    }
}

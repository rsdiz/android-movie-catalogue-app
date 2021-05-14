package id.rosyid.moviecatalogue.ui.homepage.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rosyid.moviecatalogue.data.entities.Movie
import id.rosyid.moviecatalogue.data.repository.MoviesRepository
import id.rosyid.moviecatalogue.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private var _listMovies = MutableLiveData<Resource<List<Movie>>>()
    val listMovies: LiveData<Resource<List<Movie>>>
        get() = _listMovies

    fun setUp() {
        _listMovies = moviesRepository.getAllMovies() as MutableLiveData<Resource<List<Movie>>>
    }
}

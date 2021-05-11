package id.rosyid.moviecatalogue.ui.homepage.movies

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rosyid.moviecatalogue.data.entities.Movie
import id.rosyid.moviecatalogue.data.repository.MoviesRepository
import id.rosyid.moviecatalogue.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _listMovies = moviesRepository.getAllMovies() as MutableLiveData<Resource<List<Movie>>>
    val listMovies: LiveData<Resource<List<Movie>>> = _listMovies
}

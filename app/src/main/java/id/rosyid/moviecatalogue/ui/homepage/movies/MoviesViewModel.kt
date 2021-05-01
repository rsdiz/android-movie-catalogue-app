package id.rosyid.moviecatalogue.ui.homepage.movies

import androidx.lifecycle.ViewModel
import id.rosyid.moviecatalogue.data.MovieEntity
import id.rosyid.moviecatalogue.utils.MoviesData

class MoviesViewModel : ViewModel() {
    fun getMovies(): List<MovieEntity> = MoviesData.generateMovies()
}

package id.rosyid.moviecatalogue.ui.homepage.movies

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest {
    private lateinit var moviesViewModel: MoviesViewModel
    private val totalMovies = 13

    @Before
    fun setUp() {
        moviesViewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val moviesList = moviesViewModel.getMovies()
        assertNotNull(moviesList)
        assertEquals(totalMovies, moviesList.size)
    }
}

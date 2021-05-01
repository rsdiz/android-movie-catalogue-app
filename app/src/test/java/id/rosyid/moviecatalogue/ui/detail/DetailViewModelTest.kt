package id.rosyid.moviecatalogue.ui.detail

import id.rosyid.moviecatalogue.data.MovieEntity
import id.rosyid.moviecatalogue.data.TvEntity
import id.rosyid.moviecatalogue.utils.MoviesData
import id.rosyid.moviecatalogue.utils.TvSeriesData
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel
    private val dummyMovies = MoviesData.getMovie(1)
    private val movieId = dummyMovies.movieId
    private val dummyTvSeries = TvSeriesData.getTvSeries(1)
    private val tvId = dummyTvSeries.tvId

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
    }

    @Test
    fun getMoviesDetail() {
        detailViewModel.setSelectedId(movieId, DetailActivity.TYPE_MOVIES)
        val selectedMovie: MovieEntity = detailViewModel.getItemDetail() as MovieEntity
        assertNotNull(selectedMovie)
        assertEquals(dummyMovies.movieId, selectedMovie.movieId)
        assertEquals(dummyMovies.releaseDate, selectedMovie.releaseDate)
        assertEquals(dummyMovies.title, selectedMovie.title)
        assertEquals(dummyMovies.runtime, selectedMovie.runtime)
        assertEquals(dummyMovies.poster, selectedMovie.poster)
        assertEquals(dummyMovies.overview, selectedMovie.overview)
        assertEquals(dummyMovies.status, selectedMovie.status)
        assertEquals(dummyMovies.originalLanguage, selectedMovie.originalLanguage)
        assertEquals(dummyMovies.userScore, selectedMovie.userScore)
        assertEquals(dummyMovies.genres, selectedMovie.genres)
        assertEquals(dummyMovies.keywords, selectedMovie.keywords)
        assertEquals(dummyMovies.credits, selectedMovie.credits)
        assertEquals(dummyMovies.socialLinks, selectedMovie.socialLinks)
    }

    @Test
    fun getTvSeriesDetail() {
        detailViewModel.setSelectedId(tvId, DetailActivity.TYPE_TVSERIES)
        val selectedTvSeries: TvEntity = detailViewModel.getItemDetail() as TvEntity
        assertNotNull(selectedTvSeries)
        assertEquals(dummyTvSeries.tvId, selectedTvSeries.tvId)
        assertEquals(dummyTvSeries.type, selectedTvSeries.type)
        assertEquals(dummyTvSeries.title, selectedTvSeries.title)
        assertEquals(dummyTvSeries.runtime, selectedTvSeries.runtime)
        assertEquals(dummyTvSeries.poster, selectedTvSeries.poster)
        assertEquals(dummyTvSeries.overview, selectedTvSeries.overview)
        assertEquals(dummyTvSeries.status, selectedTvSeries.status)
        assertEquals(dummyTvSeries.originalLanguage, selectedTvSeries.originalLanguage)
        assertEquals(dummyTvSeries.userScore, selectedTvSeries.userScore)
        assertEquals(dummyTvSeries.genres, selectedTvSeries.genres)
        assertEquals(dummyTvSeries.keywords, selectedTvSeries.keywords)
        assertEquals(dummyTvSeries.credits, selectedTvSeries.credits)
        assertEquals(dummyTvSeries.socialLinks, selectedTvSeries.socialLinks)
    }
}

package id.rosyid.moviecatalogue.data.remote.api

import id.rosyid.moviecatalogue.BuildConfig
import id.rosyid.moviecatalogue.data.entities.MovieDetail
import id.rosyid.moviecatalogue.data.entities.Movies
import id.rosyid.moviecatalogue.data.entities.TvDetail
import id.rosyid.moviecatalogue.data.entities.Tvs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMdbService {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.TMDB_API_KEY
    }

    @GET("discover/movie")
    suspend fun getMovies(): Response<Movies>

    @GET("discover/tv")
    suspend fun getTvs(): Response<Tvs>

    @GET("movie/{id}")
    suspend fun getMovieDetailById(@Path("id") id: Int): Response<MovieDetail>

    @GET("tv/{id}")
    suspend fun getTvDetailById(@Path("id") id: Int): Response<TvDetail>
}

package id.rosyid.moviecatalogue.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.rosyid.moviecatalogue.data.local.*
import id.rosyid.moviecatalogue.data.remote.api.TMdbService
import id.rosyid.moviecatalogue.data.remote.api.TMdbService.Companion.API_KEY
import id.rosyid.moviecatalogue.data.remote.api.TMdbService.Companion.BASE_URL
import id.rosyid.moviecatalogue.data.remote.datasource.MoviesDataSource
import id.rosyid.moviecatalogue.data.remote.datasource.TvsDataSource
import id.rosyid.moviecatalogue.data.repository.MoviesRepository
import id.rosyid.moviecatalogue.data.repository.TvRepository
import id.rosyid.moviecatalogue.utils.TokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideClient(interceptor: Interceptor): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    fun provideInterceptor(): Interceptor = TokenInterceptor(API_KEY)

    @Singleton
    @Provides
    fun provideNetworkService(retrofit: Retrofit): TMdbService = retrofit.create(TMdbService::class.java)

    @Singleton
    @Provides
    fun provideMoviesDataSource(tMdbService: TMdbService) = MoviesDataSource(tMdbService)

    @Singleton
    @Provides
    fun provideTvsDataSource(tMdbService: TMdbService) = TvsDataSource(tMdbService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideMoviesDao(database: AppDatabase) = database.moviesDao()

    @Singleton
    @Provides
    fun provideMovieDetailDao(database: AppDatabase) = database.movieDetailDao()

    @Singleton
    @Provides
    fun provideTvsDao(database: AppDatabase) = database.tvsDao()

    @Singleton
    @Provides
    fun provideTvDetailDao(database: AppDatabase) = database.tvDetailDao()

    @Singleton
    @Provides
    fun provideMoviesRepository(
        moviesDataSource: MoviesDataSource,
        moviesDao: IMoviesDao,
        movieDetailDao: IMovieDetailDao
    ) = MoviesRepository(moviesDataSource, moviesDao, movieDetailDao)

    @Singleton
    @Provides
    fun provideTvRepository(
        tvsDataSource: TvsDataSource,
        tvsDao: ITvsDao,
        tvDetailDao: ITvDetailDao
    ) = TvRepository(tvsDataSource, tvsDao, tvDetailDao)
}

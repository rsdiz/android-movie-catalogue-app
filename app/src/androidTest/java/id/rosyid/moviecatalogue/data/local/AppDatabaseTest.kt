package id.rosyid.moviecatalogue.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import id.rosyid.moviecatalogue.utils.DataDummy
import id.rosyid.moviecatalogue.utils.getOrAwaitValue
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {
    private lateinit var db: AppDatabase
    private lateinit var tvsDao: ITvsDao
    private lateinit var tvDetailDao: ITvDetailDao
    private lateinit var moviesDao: IMoviesDao
    private lateinit var movieDetailDao: IMovieDetailDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        tvsDao = db.tvsDao()
        tvDetailDao = db.tvDetailDao()
        moviesDao = db.moviesDao()
        movieDetailDao = db.movieDetailDao()
    }

    @After
    public override fun tearDown() {
        db.close()
    }

    @Test
    fun tvsDao() = runBlocking {
        val tvsDummy = DataDummy.generateListTv()
        tvsDao.insertAll(tvsDummy)
        val listTvs = tvsDao.getAllTvs().getOrAwaitValue()
        assertEquals(listTvs.size, tvsDummy.size)
    }

    @Test
    fun tvDetailDao() = runBlocking {
        val tvDetailDummy = DataDummy.getTvDetail()
        tvDetailDao.insert(tvDetailDummy)
        val tvDetail = tvDetailDao.getDetailById(tvDetailDummy.id).getOrAwaitValue()
        assertEquals(tvDetail, tvDetailDummy)
    }

    @Test
    fun moviesDao() = runBlocking {
        val moviesDummy = DataDummy.generateListMovie()
        moviesDao.insertAll(moviesDummy)
        val listMovies = moviesDao.getAllMovies().getOrAwaitValue()
        assertEquals(listMovies.size, moviesDummy.size)
    }

    @Test
    fun movieDetailDao() = runBlocking {
        val movieDetailDummy = DataDummy.getMovieDetail()
        movieDetailDao.insert(movieDetailDummy)
        val movieDetail = movieDetailDao.getDetail(movieDetailDummy.id).getOrAwaitValue()
        assertEquals(movieDetail, movieDetailDummy)
    }
}

package id.rosyid.moviecatalogue.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.rosyid.moviecatalogue.data.entities.MovieDetail
import id.rosyid.moviecatalogue.data.entities.Movies
import id.rosyid.moviecatalogue.data.entities.TvDetail
import id.rosyid.moviecatalogue.data.entities.Tvs

@Database(entities = [Tvs::class, Movies::class, TvDetail::class, MovieDetail::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tvsDao(): TvsDao
    abstract fun tvDetailDao(): TvDetailDao
    abstract fun moviesDao(): MoviesDao
    abstract fun movieDetailDao(): MovieDetailDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "movie_catalogue")
                .fallbackToDestructiveMigration()
                .build()
    }
}

package id.rosyid.moviecatalogue.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.rosyid.moviecatalogue.data.entities.*
import id.rosyid.moviecatalogue.utils.GenreConverter
import id.rosyid.moviecatalogue.utils.IntConverter

@Database(
    entities = [Tv::class, Movie::class, TvDetail::class, MovieDetail::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenreConverter::class, IntConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tvsDao(): ITvsDao
    abstract fun tvDetailDao(): ITvDetailDao
    abstract fun moviesDao(): IMoviesDao
    abstract fun movieDetailDao(): IMovieDetailDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

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

package id.rosyid.moviecatalogue.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.rosyid.moviecatalogue.data.entities.Genre

class GenreConverter {
    private var gson = Gson()

    @TypeConverter
    fun genreItemToString(genreItems: List<Genre>): String {
        return gson.toJson(genreItems)
    }

    @TypeConverter
    fun stringToGenreItem(data: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {
        }.type
        return gson.fromJson(data, listType)
    }
}

class IntConverter {
    private var gson = Gson()

    @TypeConverter
    fun IntegerItemToString(genreItems: List<Int>): String {
        return gson.toJson(genreItems)
    }

    @TypeConverter
    fun stringToIntItem(data: String): List<Int> {
        val listType = object : TypeToken<List<Int>>() {
        }.type
        return gson.fromJson(data, listType)
    }
}

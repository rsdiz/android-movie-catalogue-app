package id.rosyid.moviecatalogue.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tv_detail")
data class TvDetail(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    @PrimaryKey val id: Int,
    val name: String,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    val status: String,
    val tagline: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

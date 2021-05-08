package id.rosyid.moviecatalogue.data.entities

import com.google.gson.annotations.SerializedName

data class TvDetail(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("created_by")
    val createdBy: List<Credit>,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val name: String,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val status: String,
    val tagline: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

data class Credit(
    @SerializedName("credit_id")
    val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String
)

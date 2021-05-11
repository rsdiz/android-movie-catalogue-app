package id.rosyid.moviecatalogue.ui.detail

import id.rosyid.moviecatalogue.data.entities.Genre

data class DetailModel(
    val title: String = "",
    val backdropPath: String? = null,
    val posterPath: String? = null,
    val tagline: String = "",
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val date: String = "",
    val overview: String = "",
    val voteAverage: Float = 0F,
    val voteCount: Int = 0
)

package id.rosyid.moviecatalogue.data

import java.util.*

data class MovieEntity(
    var movieId: Int,
    var title: String,
    var releaseDate: Date,
    var runtime: String,
    var poster: Int,
    var overview: String,
    var status: String,
    var originalLanguage: String,
    var userScore: Int
) {
    var genres: List<GenreEntity>? = null
    var keywords: List<KeywordEntity>? = null
    var credits: List<CreditEntity>? = null
    var socialLinks: List<SocialLinkEntity>? = null
}

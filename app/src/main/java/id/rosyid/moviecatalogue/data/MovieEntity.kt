package id.rosyid.moviecatalogue.data

import java.time.LocalDate

data class MovieEntity(
    var movieId: Int,
    var title: String,
    var releaseDate: LocalDate,
    var runtime: String,
    var poster: Int,
    var overview: String,
    var status: String,
    var originalLanguage: String,
    var userScore: Int,
    var genres: List<GenreEntity>,
    var keywords: List<KeywordEntity>,
    var credits: List<CreditEntity>,
    var socialLinks: List<SocialLinkEntity>
)

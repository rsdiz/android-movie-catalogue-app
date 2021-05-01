package id.rosyid.moviecatalogue.data

import java.time.LocalDate

data class MovieEntity(
    var movieId: Int,
    override var title: String,
    var releaseDate: LocalDate,
    override var runtime: String,
    override var poster: Int,
    override var overview: String,
    override var status: String,
    override var originalLanguage: String,
    override var userScore: Int,
    override var genres: List<GenreEntity>,
    override var keywords: List<KeywordEntity>,
    override var credits: List<CreditEntity>,
    override var socialLinks: List<SocialLinkEntity>
) : BaseEntity(
    title,
    runtime,
    poster,
    overview,
    status,
    originalLanguage,
    userScore,
    genres,
    keywords,
    credits,
    socialLinks
)

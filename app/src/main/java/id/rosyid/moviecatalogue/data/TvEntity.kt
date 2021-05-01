package id.rosyid.moviecatalogue.data

data class TvEntity(
    var tvId: Int,
    override var title: String,
    override var runtime: String,
    override var poster: Int,
    override var overview: String,
    override var status: String,
    override var originalLanguage: String,
    override var userScore: Int,
    var type: String,
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

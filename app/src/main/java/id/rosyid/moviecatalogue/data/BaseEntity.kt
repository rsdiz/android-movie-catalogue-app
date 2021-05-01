package id.rosyid.moviecatalogue.data

open class BaseEntity(
    open var title: String,
    open var runtime: String,
    open var poster: Int,
    open var overview: String,
    open var status: String,
    open var originalLanguage: String,
    open var userScore: Int,
    open var genres: List<GenreEntity>,
    open var keywords: List<KeywordEntity>,
    open var credits: List<CreditEntity>,
    open var socialLinks: List<SocialLinkEntity>
)

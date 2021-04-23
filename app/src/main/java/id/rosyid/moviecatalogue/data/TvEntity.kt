package id.rosyid.moviecatalogue.data

data class TvEntity(
    var tvId: Int,
    var title: String,
    var runtime: String,
    var poster: Int,
    var overview: String,
    var status: String,
    var originalLanguage: String,
    var userScore: Int,
    var type: String,
    var genres: List<GenreEntity>,
    var keywords: List<KeywordEntity>,
    var credits: List<CreditEntity>,
    var socialLinks: List<SocialLinkEntity>
)

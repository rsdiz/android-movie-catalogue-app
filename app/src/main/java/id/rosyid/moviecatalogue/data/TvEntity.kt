package id.rosyid.moviecatalogue.data

import id.rosyid.moviecatalogue.utils.TypeTvSeries
import java.time.LocalDate

data class TvEntity(
    var tvId: Int,
    var title: String,
    var releaseDate: LocalDate,
    var runtime: String,
    var poster: Int,
    var overview: String,
    var status: String,
    var originalLanguage: String,
    var userScore: Int,
    var type: TypeTvSeries,
    var genres: List<GenreEntity>,
    var keywords: List<KeywordEntity>,
    var credits: List<CreditEntity>,
    var socialLinks: List<SocialLinkEntity>
)

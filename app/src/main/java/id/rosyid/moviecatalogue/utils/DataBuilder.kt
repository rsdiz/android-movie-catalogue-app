package id.rosyid.moviecatalogue.utils

import id.rosyid.moviecatalogue.data.CreditEntity
import id.rosyid.moviecatalogue.data.GenreEntity
import id.rosyid.moviecatalogue.data.KeywordEntity
import id.rosyid.moviecatalogue.data.SocialLinkEntity

object DataBuilder {
    /**
     * Generate String to List of GenreEntity
     *
     * @param name - genre name
     * @return list of GenreEntity
     */
    fun buildGenres(vararg name: String): List<GenreEntity> {
        val listGenre: MutableList<GenreEntity> = mutableListOf()
        name.forEach {
            listGenre.add(GenreEntity(it))
        }
        return listGenre
    }

    /**
     * Generate String to list of KeywordEntity
     *
     * @param keyword - keyword name
     * @return list of KeywordEntity
     */
    fun buildKeywords(vararg keyword: String): List<KeywordEntity> {
        val listKeyword: MutableList<KeywordEntity> = mutableListOf()
        keyword.forEach {
            listKeyword.add(KeywordEntity(it))
        }
        return listKeyword
    }

    /**
     * Generate array of string to list of CreditEntity
     *
     * @param credits - Array of String must be [name, position]
     * @return list of CreditEntity
     */
    fun buildCredits(vararg credits: Array<String>): List<CreditEntity> {
        val listCredits: MutableList<CreditEntity> = mutableListOf()
        credits.forEach { credit ->
            val creditEntity = CreditEntity(credit[0], credit[1])
            listCredits.add(creditEntity)
        }
        return listCredits
    }

    /**
     * Generate array of Any to list of SocialLinkEntity
     *
     * @param socialLinks - array of Any must be [name: TypeSocialLink, link: String]
     * @return list of SocialLinkEntity
     */
    fun buildSocialLink(vararg socialLinks: Array<Any>): List<SocialLinkEntity> {
        val listSocialLink: MutableList<SocialLinkEntity> = mutableListOf()
        socialLinks.forEach {
            val creditEntity = SocialLinkEntity(it[0] as TypeSocialLink, it[1] as String)
            listSocialLink.add(creditEntity)
        }
        return listSocialLink
    }
}

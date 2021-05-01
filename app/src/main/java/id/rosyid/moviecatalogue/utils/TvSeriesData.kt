package id.rosyid.moviecatalogue.utils

import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.data.TvEntity
import id.rosyid.moviecatalogue.utils.DataBuilder.buildCredits
import id.rosyid.moviecatalogue.utils.DataBuilder.buildGenres
import id.rosyid.moviecatalogue.utils.DataBuilder.buildKeywords
import id.rosyid.moviecatalogue.utils.DataBuilder.buildSocialLink

object TvSeriesData {
    private val listTvSeries = generateTvSeries()

    fun generateTvSeries(): List<TvEntity> {
        return mutableListOf(
            TvEntity(
                1,
                "Arrow",
                "42m",
                R.drawable.poster_arrow,
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Ended",
                "English",
                66,
                "Scripted",
                buildGenres("Crime", "Drama", "Mystery", "Action", "Adventure"),
                buildKeywords(
                    "dc comics",
                    "superhero",
                    "based on comic",
                    "superhero team",
                    "masked vigilante"
                ),
                buildCredits(
                    arrayOf("Greg Berlanti", "Creator"),
                    arrayOf("Marc Guggenheim", "Creator"),
                    arrayOf("Andrew Kreisberg", "Creator")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/CWArrow"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/CW_Arrow"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/cw_arrow/"),
                    arrayOf(TypeSocialLink.JustWatch, "https://www.justwatch.com/id/tv-show/arrow"),
                    arrayOf(TypeSocialLink.Homepage, "http://www.cwtv.com/shows/arrow")
                )
            ),
            TvEntity(
                2,
                "Doom Patrol",
                "49m",
                R.drawable.poster_doom_patrol,
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Returning Series",
                "English",
                76,
                "Scripted",
                buildGenres("Sci-Fi & Fantasy", "Comedy", "Drama"),
                buildKeywords(
                    "dc comics",
                    "superhero",
                    "based on comic",
                    "misfit",
                    "superhero team",
                    "weird science"
                ),
                buildCredits(arrayOf("Jeremy Carver", "Creator")),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/DCDoomPatrol"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/DCDoomPatrol"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/dcdoompatrol/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/tv-show/doom-patrol"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "https://www.hbomax.com/doompatrol")
                )
            ),
            TvEntity(
                3,
                "Dragon Ball",
                "25m",
                R.drawable.poster_dragon_ball,
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "Ended",
                "Japanese",
                81,
                "Scripted",
                buildGenres("Animation", "Action & Adventure", "Sci-Fi & Fantasy"),
                buildKeywords(
                    "martial arts",
                    "kung fu",
                    "monster",
                    "flying",
                    "competition",
                    "karate",
                    "rivalry",
                    "alien",
                    "anthropomorphism",
                    "tournament",
                    "dragon",
                    "based on manga",
                    "fighting",
                    "combat",
                    "super power",
                    "shounen",
                    "anime"
                ),
                buildCredits(arrayOf("Akira Toriyama", "Creator")),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Homepage, "http://www.dragonballofficial.com/")
                )
            ),
            TvEntity(
                4,
                "Fairy Tail",
                "25m",
                R.drawable.poster_fairytail,
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "Ended",
                "Japanese",
                78,
                "Scripted",
                buildGenres(
                    "Action & Adventure",
                    "Animation",
                    "Comedy",
                    "Sci-Fi & Fantasy",
                    "Mystery"
                ),
                buildKeywords(
                    "magic",
                    "anthropomorphism",
                    "based on manga",
                    "storytelling",
                    "wizard",
                    "ecchi",
                    "magical girl",
                    "shounen",
                    "anime",
                    "time skip"
                ),
                buildCredits(),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/fairytail_pr"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/tv-show/fairy-tail"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://www.tv-tokyo.co.jp/anime/fairytail/")
                )
            ),
            TvEntity(
                5,
                "Family Guy",
                "22m",
                R.drawable.poster_family_guy,
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "Returning Series",
                "English",
                70,
                "Scripted",
                buildGenres("Animation", "Comedy"),
                buildKeywords(
                    "adult humor",
                    "satire",
                    "parody",
                    "middle class",
                    "dysfunctional family",
                    "social satire",
                    "boy genius",
                    "adult animation"
                ),
                buildCredits(arrayOf("Seth MacFarlane", "Creator")),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, ""),
                    arrayOf(TypeSocialLink.Twitter, ""),
                    arrayOf(TypeSocialLink.Instagram, ""),
                    arrayOf(TypeSocialLink.JustWatch, ""),
                    arrayOf(TypeSocialLink.Homepage, "")
                )
            ),
            TvEntity(
                6,
                "The Flash",
                "44m",
                R.drawable.poster_flash,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "Returning Series",
                "English",
                77,
                "Scripted",
                buildGenres("Drama", "Sci-Fi & Fantasy"),
                buildKeywords(
                    "dc comics",
                    "superhero",
                    "speed",
                    "based on comic",
                    "super power",
                    "masked superhero",
                    "supervillain"
                ),
                buildCredits(
                    arrayOf("Greg Berlanti", "Creator"),
                    arrayOf("Geoff Johns", "Creator"),
                    arrayOf("Andrew Kreisberg", "Creator")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/CWTheFlash"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/CW_TheFlash"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/cwtheflash/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/tv-show/the-flash"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://www.cwtv.com/shows/the-flash/")
                )
            ),
            TvEntity(
                7,
                "Game of Thrones",
                "1h",
                R.drawable.poster_god,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Ended",
                "English",
                84,
                "Scripted",
                buildGenres("Sci-Fi & Fantasy", "Drama", "Action & Adventure"),
                buildKeywords(
                    "based on novel or book",
                    "kingdom",
                    "dragon",
                    "king",
                    "intrigue",
                    "fantasy world"
                ),
                buildCredits(
                    arrayOf("David Benioff", "Creator"),
                    arrayOf("D. B. Weiss", "Creator")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/GameOfThrones"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/GameOfThrones"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/gameofthrones/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/tv-show/game-of-thrones"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://www.hbo.com/game-of-thrones")
                )
            ),
            TvEntity(
                8,
                "Gotham",
                "43m",
                R.drawable.poster_gotham,
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Ended",
                "English",
                75,
                "Scripted",
                buildGenres("Drama", "Crime", "Sci-Fi & Fantasy"),
                buildKeywords(
                    "dc comics",
                    "insane asylum",
                    "based on comic",
                    "super power",
                    "super villain",
                    "origin story"
                ),
                buildCredits(arrayOf("Bruno Heller", "Creator")),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/GOTHAMonFOX"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/Gotham"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/gothamonfox/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/tv-show/gotham"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://www.fox.com/gotham/")
                )
            ),
            TvEntity(
                9,
                "Grey\'s Anatomy",
                "43m",
                R.drawable.poster_grey_anatomy,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Returning Series",
                "English",
                82,
                "Scripted",
                buildGenres("Drama"),
                buildKeywords(
                    "seattle",
                    "trauma",
                    "workplace",
                    "hospital",
                    "doctor",
                    "medical student",
                    "surgical interns",
                    "medical drama"
                ),
                buildCredits(arrayOf("Shonda Rhimes", "Creator")),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/GreysAnatomy"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/greysabc"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/greysabc/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/tv-show/greys-anatomy"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://abc.go.com/shows/greys-anatomy")
                )
            ),
            TvEntity(
                10,
                "Hanna",
                "50m",
                R.drawable.poster_hanna,
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "Returning Series",
                "English",
                75,
                "Scripted",
                buildGenres("Action & Adventure", "Drama"),
                buildKeywords(
                    "underage soldier",
                    "coming of age",
                    "thriller",
                    "based on movie",
                    "child soldier",
                    "vengeance"
                ),
                buildCredits(arrayOf("David Farr", "Creator")),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/HannaOnPrime"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/hannaonprime"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/hannaonprime/"),
                    arrayOf(TypeSocialLink.JustWatch, "https://www.justwatch.com/id/tv-show/hanna"),
                    arrayOf(TypeSocialLink.Homepage, "https://www.amazon.com/dp/B07L5WLYG6/")
                )
            )
        )
    }

    fun getTvSeries(tvId: Int): TvEntity =
        listTvSeries[listTvSeries.binarySearch { compareValues(it.tvId, tvId) }]
}

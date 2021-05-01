package id.rosyid.moviecatalogue.utils

import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.data.MovieEntity
import id.rosyid.moviecatalogue.utils.DataBuilder.buildCredits
import id.rosyid.moviecatalogue.utils.DataBuilder.buildGenres
import id.rosyid.moviecatalogue.utils.DataBuilder.buildKeywords
import id.rosyid.moviecatalogue.utils.DataBuilder.buildSocialLink
import java.time.LocalDate

object MoviesData {
    private val listMovies = generateMovies()

    fun generateMovies(): List<MovieEntity> {
        return mutableListOf(
            MovieEntity(
                1,
                "A Star is Born",
                LocalDate.parse("2018-10-03"),
                "2h 16m",
                R.drawable.poster_a_start_is_born,
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "Released",
                "English",
                75,
                buildGenres("Drama", "Romance", "Music"),
                buildKeywords(
                    "country music", "waitress", "self destruction", "talent",
                    "pop star", "concert", "addiction", "alcoholism", "remake", "death of father",
                    "aspiring singer", "singer", "fame", "tinnitus", "falling in love",
                    "insecurity", "alcoholic", "death of mother", "aspiration", "death of parent",
                    "showbiz", "emotional vulnerability", "hearing impaired",
                    "brother brother relationship"
                ),
                buildCredits(
                    arrayOf("Bradley Cooper", "Director, Screenplay"),
                    arrayOf("Will Fetters", "Screenplay"),
                    arrayOf("Eric Roth", "Screenplay"),
                    arrayOf("William A. Wellman", "Story"),
                    arrayOf("Robert Carson", "Story")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/starisbornmovie"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/starisbornmovie"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/starisbornmovie/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/a-star-is-born-2018"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://astarisbornmovie.com/")
                )
            ),
            MovieEntity(
                2,
                "Alita: Battle Angel",
                LocalDate.parse("2019-02-14"),
                "2h 2m",
                R.drawable.poster_alita,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Released",
                "English",
                72,
                buildGenres("Action", "Science Fiction", "Adventure"),
                buildKeywords(
                    "martial arts", "bounty hunter", "extreme sports", "dystopia",
                    "superhero", "cyberpunk", "based on manga", "female cyborg",
                    "live action remake", "floating city", "manga"
                ),
                buildCredits(
                    arrayOf("Robert Rodriguez", "Director"),
                    arrayOf("James Cameron", "Screenplay"),
                    arrayOf("Laeta Kalogridis", "Screenplay")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/AlitaMovie"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/AlitaMovie"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/AlitaMovie/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/alita-battle-angel"
                    ),
                    arrayOf(
                        TypeSocialLink.Homepage,
                        "https://www.foxmovies.com/movies/alita-battle-angel"
                    )
                )
            ),
            MovieEntity(
                3,
                "Aquaman",
                LocalDate.parse("2018-12-21"),
                "2h 23m",
                R.drawable.poster_aquaman,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Released",
                "English",
                69,
                buildGenres("Action", "Adventure", "Fantasy"),
                buildKeywords(
                    "dc comics",
                    "hero",
                    "atlantis",
                    "half-brother",
                    "superhero",
                    "based on comic",
                    "royalty",
                    "shark",
                    "duringcreditsstinger",
                    "dc extended universe"
                ),
                buildCredits(
                    arrayOf("James Wan", "Director, Story"),
                    arrayOf("Will Beall", "Screenplay, Story"),
                    arrayOf("Paul Norris", "Characters"),
                    arrayOf("Mort Weisinger", "Characters"),
                    arrayOf("David Leslie Johnson-McGoldrick", "Screenplay"),
                    arrayOf("Geoff Johns", "Story"),
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/Aquamanmovie"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/Aquamanmovie"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/Aquamanmovie/"),
                    arrayOf(TypeSocialLink.JustWatch, "https://www.justwatch.com/id/movie/aquaman"),
                    arrayOf(TypeSocialLink.Homepage, "http://www.aquamanmovie.com/")
                )
            ),
            MovieEntity(
                4,
                "Bohemian Rhapsody",
                LocalDate.parse("2018-02-11"),
                "2h 15m",
                R.drawable.poster_bohemian,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Released",
                "English",
                80,
                buildGenres("Music", "Drama", "History"),
                buildKeywords(
                    "london, england",
                    "aids",
                    "1970s",
                    "queen",
                    "musician",
                    "biography",
                    "based on a true story",
                    "hiv",
                    "male homosexuality",
                    "singer",
                    "fame",
                    "rock band",
                    "music band",
                    "lgbt",
                    "1980s",
                    "parsi",
                    "gay theme",
                    "gay",
                    "2018"
                ),
                buildCredits(
                    arrayOf("Anthony McCarten", "Screenplay, Story"),
                    arrayOf("Bryan Singer", "Director"),
                    arrayOf("Peter Morgan", "Story")
                ),
                buildSocialLink(
                    arrayOf(
                        TypeSocialLink.Facebook,
                        "https://www.facebook.com/BohemianRhapsodyMovie"
                    ),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/BoRhapMovie"),
                    arrayOf(
                        TypeSocialLink.Instagram,
                        "https://instagram.com/bohemianrhapsodymovie/"
                    ),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/bohemian-rhapsody"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://bohemianrhapsody.com/")
                )
            ),
            MovieEntity(
                5,
                "Cold Pursuit",
                LocalDate.parse("2019-02-08"),
                "1h 59m",
                R.drawable.poster_cold_persuit,
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Released",
                "English",
                57,
                buildGenres("Action", "Crime", "Thriller"),
                buildKeywords(
                    "colorado",
                    "drug dealer",
                    "revenge",
                    "dark comedy",
                    "gun battle",
                    "snow"
                ),
                buildCredits(
                    arrayOf("Hans Petter Moland", "Director"),
                    arrayOf("Frank Baldwin", "Screenplay")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/coldpursuitmovie"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/coldpursuit"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/coldpursuitmovie/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/cold-pursuit"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "https://coldpursuit.movie/")
                )
            ),
            MovieEntity(
                6,
                "Creed II",
                LocalDate.parse("2018-11-21"),
                "2h 10m",
                R.drawable.poster_creed,
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Released",
                "English",
                69,
                buildGenres("Drama"),
                buildKeywords(
                    "sports",
                    "sequel",
                    "rivalry",
                    "los angeles, california",
                    "boxing",
                    "dedicated",
                    "box ring",
                    "ukraine"
                ),
                buildCredits(
                    arrayOf("Sylvester Stallone", "Characters, Screenplay"),
                    arrayOf("Steven Caple Jr.", "Director"),
                    arrayOf("Juel Taylor", "Screenplay"),
                    arrayOf("Sascha Penn", "Story"),
                    arrayOf("Cheo Hodari Coker", "Story")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/creedmovie"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/creedmovie"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/creedmovie/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/creed-ii"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://creedthemovie.com/")
                )
            ),
            MovieEntity(
                7,
                "Fantastic Beasts: The Crimes of Grindelwald",
                LocalDate.parse("2018-11-16"),
                "2h 14m",
                R.drawable.poster_crimes,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "Released",
                "English",
                69,
                buildGenres("Adventure", "Fantasy", "Drama"),
                buildKeywords(
                    "paris, france",
                    "new york city",
                    "witch",
                    "world war ii",
                    "sequel",
                    "old flame",
                    "wizard",
                    "1920s"
                ),
                buildCredits(
                    arrayOf("David Yates", "Director"),
                    arrayOf("J.K. Rowling", "Screenplay")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/FantasticBeasts"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/FantasticBeasts"),
                    arrayOf(
                        TypeSocialLink.Instagram,
                        "https://instagram.com/fantasticbeastsmovie/"
                    ),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/fantastic-beasts-the-crimes-of-grindelwald"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://www.fantasticbeasts.com/")
                )
            ),
            MovieEntity(
                8,
                "Glass",
                LocalDate.parse("2019-01-18"),
                "2h 9m",
                R.drawable.poster_glass,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "Released",
                "English",
                67,
                buildGenres("Thriller", "Drama", "Science Fiction"),
                buildKeywords(
                    "villain",
                    "sequel",
                    "superhero",
                    "psychiatric hospital",
                    "comic book shop",
                    "violence",
                    "super power",
                    "mental illness",
                    "multiple personality",
                    "dissociative identity disorder",
                    "surveillance specialist"
                ),
                buildCredits(arrayOf("M. Night Shyamalan", "Director, Screenplay")),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/GlassMovie"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/GlassMovie"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/glassmovie/"),
                    arrayOf(TypeSocialLink.JustWatch, "https://www.justwatch.com/id/movie/glass"),
                    arrayOf(TypeSocialLink.Homepage, "https://www.glassmovie.com/")
                )
            ),
            MovieEntity(
                9,
                "How to Train Your Dragon: The Hidden World",
                LocalDate.parse("2019-02-22"),
                "1h 44m",
                R.drawable.poster_how_to_train,
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Released",
                "English",
                78,
                buildGenres("Animation", "Family", "Adventure"),
                buildKeywords(
                    "flying",
                    "based on novel or book",
                    "overpopulation",
                    "viking",
                    "finale",
                    "sequel",
                    "dragon",
                    "love interest",
                    "based on children's book"
                ),
                buildCredits(
                    arrayOf("Dean DeBlois", "Director, Screenplay, Story"),
                    arrayOf("Cressida Cowell", "Novel")
                ),
                buildSocialLink(
                    arrayOf(
                        TypeSocialLink.Facebook,
                        "https://www.facebook.com/HowToTrainYourDragon"
                    ),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/httydragon/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/how-to-train-your-dragon-the-hidden-world"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "https://www.howtotrainyourdragon.com/")
                )
            ),
            MovieEntity(
                10,
                "Avengers: Infinity War",
                LocalDate.parse("2018-04-27"),
                "2h 29m",
                R.drawable.poster_infinity_war,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Released",
                "English",
                83,
                buildGenres("Adventure", "Action", "Science Fiction"),
                buildKeywords(
                    "magic",
                    "sacrifice",
                    "superhero",
                    "based on comic",
                    "space",
                    "battlefield",
                    "genocide",
                    "magical object",
                    "super power",
                    "team",
                    "aftercreditsstinger",
                    "marvel cinematic universe (mcu)",
                    "cosmic"
                ),
                buildCredits(
                    arrayOf("Anthony Russo", "Director"),
                    arrayOf("Joe Russo", "Director"),
                    arrayOf("Stephen McFeely", "Screenplay"),
                    arrayOf("Christopher Markus", "Screenplay")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/avengers"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/Avengers"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/avengers/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/avengers-infinity-war"
                    ),
                    arrayOf(
                        TypeSocialLink.Homepage,
                        "https://www.marvel.com/movies/avengers-infinity-war"
                    )
                )
            ),
            MovieEntity(
                11,
                "Mary Queen of Scots",
                LocalDate.parse("2018-12-21"),
                "2h 4m",
                R.drawable.poster_mary_queen,
                "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
                "Released",
                "English",
                66,
                buildGenres("Drama", "History"),
                buildKeywords(
                    "scotland",
                    "based on novel or book",
                    "cousin",
                    "royal family",
                    "biography",
                    "queen elizabeth i",
                    "tudors",
                    "female prisoner",
                    "mary queen of scots",
                    "cousin cousin relationship",
                    "woman director",
                    "16th century",
                    "british monarchy",
                    "stuarts"
                ),
                buildCredits(
                    arrayOf("Josie Rourke", "Director"),
                    arrayOf("Beau Willimon", "Screenplay")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/MaryQueenMovie"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/MaryQueenMovie"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/maryqueenmovie/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/mary-queen-of-scots"
                    ),
                    arrayOf(
                        TypeSocialLink.Homepage,
                        "http://www.focusfeatures.com/mary-queen-of-scots/"
                    )
                )
            ),
            MovieEntity(
                12,
                "Master Z: Ip Man Legacy",
                LocalDate.parse("2018-12-26"),
                "1h 47m",
                R.drawable.poster_master_z,
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                "Released",
                "English",
                59,
                buildGenres("Action"),
                buildKeywords("martial arts"),
                buildCredits(
                    arrayOf("Yuen Woo-Ping", "Director"),
                    arrayOf("Chan Tai-Li", "Screenplay"),
                    arrayOf("Edmond Wong", "Screenplay")
                ),
                buildSocialLink(
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/master-z-ip-man-legacy"
                    )
                )
            ),
            MovieEntity(
                13,
                "Mortal Engines",
                LocalDate.parse("2018-12-14"),
                "2h 9m",
                R.drawable.poster_mortal_engines,
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "Released",
                "English",
                61,
                buildGenres("Adventure", "Science Fiction"),
                buildKeywords(
                    "based on novel or book",
                    "post-apocalyptic future",
                    "dystopia",
                    "revenge",
                    "steampunk",
                    "wasteland",
                    "futuristic vehicle",
                    "based on young adult novel"
                ),
                buildCredits(
                    arrayOf("Christian Rivers", "Director"),
                    arrayOf("Philip Reeve", "Novel"),
                    arrayOf("Philippa Boyens", "Screenplay"),
                    arrayOf("Peter Jackson", "Screenplay"),
                    arrayOf("Fran Walsh", "Screenplay")
                ),
                buildSocialLink(
                    arrayOf(TypeSocialLink.Facebook, "https://www.facebook.com/MortalEnginesMovie"),
                    arrayOf(TypeSocialLink.Twitter, "https://twitter.com/mortal_engines"),
                    arrayOf(TypeSocialLink.Instagram, "https://instagram.com/mortalengines/"),
                    arrayOf(
                        TypeSocialLink.JustWatch,
                        "https://www.justwatch.com/id/movie/mortal-engines"
                    ),
                    arrayOf(TypeSocialLink.Homepage, "http://www.mortalengines.com/")
                )
            )
        )
    }

    fun getMovie(movieId: Int): MovieEntity =
        listMovies[listMovies.binarySearch { compareValues(it.movieId, movieId) }]
}

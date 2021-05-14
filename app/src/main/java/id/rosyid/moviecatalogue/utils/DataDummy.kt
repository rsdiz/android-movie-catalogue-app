package id.rosyid.moviecatalogue.utils

import id.rosyid.moviecatalogue.data.entities.*

object DataDummy {
    fun generateListTv() =
        listOf(
            Tv(
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "2021-03-19",
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                7.9,
                5378
            ),
            Tv(
                "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                "2021-03-26",
                95557,
                "Invincible",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "/4UEfVAuI4Yn09nzJ16NFR1pv3ac.jpg",
                8.9,
                1442
            ),
            Tv(
                "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                "2014-10-07",
                60735,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7552
            ),
            Tv(
                "/zlXPNnnUlyg6KyvvjGd2ZxG6Tnw.jpg",
                "2017-09-25",
                71712,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "/53P8oHo9cfOsgb1cLxBi4pFY0ja.jpg",
                8.6,
                8314
            )
        )

    fun generateListMovie() = listOf(
        Movie(
            "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
            460465,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
            "2021-04-07",
            "Mortal Kombat",
            7.7,
            2266
        ),
        Movie(
            "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
            399566,
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            "2021-03-24",
            "Godzilla vs. Kong",
            8.1,
            5373
        ),
        Movie(
            "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            567189,
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "2021-04-29",
            "Tom Clancy's Without Remorse",
            7.3,
            696
        ),
        Movie(
            "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
            615457,
            "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
            "2021-03-26",
            "Nobody",
            8.5,
            1297
        )
    )

    fun getMovieDetail() = MovieDetail(
        "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
        listOf(
            Genre(28, "Action"),
            Genre(14, "Fantasy"),
            Genre(12, "Adventure"),
            Genre(878, "Science Fiction")
        ),
        "https://www.mortalkombatmovie.net",
        460465,
        "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
        "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
        "2021-04-07",
        110,
        "Released",
        "Get over here.",
        "Mortal Kombat",
        7.7,
        2272
    )

    fun getTvDetail() = TvDetail(
        "/1i1N0AVRb54H6ZFPDTwbo9MLxSF.jpg",
        listOf(
            36,
            30
        ),
        "2021-01-15",
        listOf(
            Genre(10765, "Sci-Fi & Fantasy"),
            Genre(9648, "Mystery"),
            Genre(18, "Drama")

        ),
        "https://www.disneyplus.com/series/wandavision/4SrN28ZjDLwH",
        85271,
        "WandaVision",
        1,
        "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
        "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
        "Ended",
        "Experience a new vision of reality.",
        8.4,
        8387
    )

    fun generateMovies() = Movies(
        1,
        generateListMovie(),
        1,
        4
    )

    fun generateTvs() = Tvs(
        1,
        generateListTv(),
        1,
        4
    )
}

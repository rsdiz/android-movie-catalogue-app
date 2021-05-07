package id.rosyid.moviecatalogue.ui.homepage

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.data.MovieEntity
import id.rosyid.moviecatalogue.data.TvEntity
import id.rosyid.moviecatalogue.ui.splash.SplashActivity
import id.rosyid.moviecatalogue.utils.FormatPattern
import id.rosyid.moviecatalogue.utils.toStringWithPattern
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Test

class HomepageActivityTest {
    private val dummyMovies = listOf<MovieEntity>()
    private val dummyTvSeries = listOf<TvEntity>()

    @Before
    fun setUp() {
        ActivityScenario.launch(SplashActivity::class.java)
        ActivityScenario.launch(HomepageActivity::class.java)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.rv_movies)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.content_title)).check(matches(isDisplayed()))
        onView(withId(R.id.content_title)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.content_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.content_release_date)).check(
            matches(
                withText(
                    dummyMovies[0].releaseDate.toStringWithPattern(
                        FormatPattern.DEFAULT_PATTERN
                    )
                )
            )
        )
        onView(withId(R.id.content_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.content_poster)).check(matches(withTagValue(equalTo(dummyMovies[0].poster))))
        onView(withId(R.id.rv_keywords)).check(RecyclerViewItemCountAssertion(dummyMovies[0].keywords.size))
    }

    @Test
    fun loadTvSeries() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_series)).perform(scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadTvSeriesDetail() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_series)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.content_title)).check(matches(isDisplayed()))
        onView(withId(R.id.content_title)).check(matches(withText(dummyTvSeries[0].title)))
        onView(withId(R.id.content_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.content_poster)).check(matches(withTagValue(equalTo(dummyTvSeries[0].poster))))
        onView(withId(R.id.rv_keywords)).check(RecyclerViewItemCountAssertion(dummyTvSeries[0].keywords.size))
    }
}

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter!!.itemCount, `is`(expectedCount))
    }
}

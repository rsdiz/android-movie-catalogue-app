package id.rosyid.moviecatalogue.ui.homepage

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.ui.splash.SplashActivity
import id.rosyid.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomepageActivityTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(SplashActivity::class.java)
        ActivityScenario.launch(HomepageActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.banner_image)).check(matches(isDisplayed()))
        onView(withId(R.id.content_title)).check(matches(isDisplayed()))
        onView(withId(R.id.content_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.content_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.content_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.content_overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvSeries() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_series)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    @Test
    fun loadDetailTvSeries() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_series)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.banner_image)).check(matches(isDisplayed()))
        onView(withId(R.id.content_title)).check(matches(isDisplayed()))
        onView(withId(R.id.content_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.content_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.content_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.content_overview)).check(matches(isDisplayed()))
    }
}

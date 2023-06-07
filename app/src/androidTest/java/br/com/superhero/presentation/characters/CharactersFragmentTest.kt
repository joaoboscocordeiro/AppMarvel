package br.com.superhero.presentation.characters

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.superhero.R
import br.com.superhero.extension.asJsonString
import br.com.superhero.framework.di.BaseUrlModule
import br.com.superhero.framework.di.CoroutinesModule
import br.com.superhero.launchFragmentInHiltContainer
import br.com.superhero.presentation.characters.adapters.CharactersViewHolder
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by João Bosco on 13/04/2023.
 */
@RunWith(AndroidJUnit4::class)
@UninstallModules(BaseUrlModule::class, CoroutinesModule::class)
@HiltAndroidTest
class CharactersFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var server: MockWebServer

    val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )

    @Before
    fun setUp() {
        server = MockWebServer().apply {
            start(8080)
        }
        launchFragmentInHiltContainer<CharactersFragment>(
            navHostController = navController
        )
    }

    @Test
    fun shouldShowCharacters_whenViewIsCreated(): Unit = runBlocking {
        // Arrange
        server.enqueue(MockResponse().setBody("characters_p1.json".asJsonString()))

        // Action
        delay(500)
        onView(
            withId(R.id.recycler_characters)
        ).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun shouldLoadMoreCharacters_whenNewPageIsRequested(): Unit = runBlocking {
        // Arrange
        with(server) {
            enqueue(MockResponse().setBody("characters_p1.json".asJsonString()))
            enqueue(MockResponse().setBody("characters_p2.json".asJsonString()))
        }

        // Action
        delay(500)
        onView(
            withId(R.id.recycler_characters)
        ).perform(
            RecyclerViewActions
                .scrollToPosition<CharactersViewHolder>(20)
        )

        // Assert
        onView(
            withText("Ajak")
        ).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun shouldShowErrorView_whenReceivesAnErrorFromApi(): Unit = runBlocking {
        // Arrange
        server.enqueue(MockResponse().setResponseCode(404))
        delay(500)
        onView(
            withId(R.id.text_initial_loading_error)
        ).check(
            matches(isDisplayed())
        )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}
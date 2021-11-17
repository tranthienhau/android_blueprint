package ago.droid.blueprint.pages.home

import ago.droid.blueprint.R
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import ago.droid.blueprint.pages.MainActivity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.hamcrest.core.StringContains.containsString
import org.junit.*
import org.junit.Assert.assertEquals

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeUITest {


    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()

    }

    @Test
    fun checkUI_isMatchDesign() {
        onView(withId(R.id.text_home4)).check(matches(withText("Sollicitudin in tortor.")))
        onView(withId(R.id.textView5)).check(matches(withText(containsString("Lorem ipsum"))))
    }

    @Test
    fun buttonClick_isNavigatedToNotification() {

        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the HomeFragment
        val homeScenario = launchFragmentInContainer<HomeFragment>()

        homeScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.mobile_navigation)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        onView(withId(R.id.btnNotification)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.action_navigation_home_to_navigation_notifications)
    }
}
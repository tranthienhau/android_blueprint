package ago.droid.blueprint.pages.notifications

import ago.droid.blueprint.R
import ago.droid.blueprint.Util.CustomComparator.withTextColor
import ago.droid.blueprint.Util.CustomComparator.withTextSize
import ago.droid.blueprint.Util.Util
import ago.droid.blueprint.pages.MainActivity
import ago.droid.blueprint.pages.home.HomeFragment
import android.graphics.Color
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.core.StringContains
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.core.content.ContextCompat

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher


@RunWith(AndroidJUnit4::class)
@LargeTest
class NotificationUITest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
        onView(withId(R.id.btnNotification)).perform(click())

    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun checkUI_isMatchDesign() {

        onView(withId(R.id.textView22))
            .check(matches(withText("Massa risus.")))
            .check(matches(withTextColor(Color.parseColor("#FCFCFC"))))
        onView(withId(R.id.textView21))
            .check(matches(withTextSize(24f)))
    }

    @Test
    fun checkUI_isNavigated() {
        Espresso.pressBack()
        onView(withId(R.id.text_home4)).check(matches(isDisplayed()))
    }


}
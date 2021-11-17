package ago.droid.blueprint.Util

import android.view.View
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object CustomComparator {
    @JvmStatic
    fun withTextColor(expectedId: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            override fun matchesSafely(textView: TextView): Boolean {
//                val colorId = ContextCompat.getColor(textView.context, expectedId)
                return textView.currentTextColor == expectedId
            }

            override fun describeTo(description: Description) {
                description.appendText("with text color: ")
                description.appendValue( expectedId)
            }
        }
    }

    @JvmStatic
    fun withTextSize(extectedSize: Float): Matcher<View?>? {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            private var realData = 0f;
            override fun matchesSafely(textView: TextView): Boolean {
                realData = textView.textSize;
                return textView.textSize.toInt() == Util.dpiToPx(ApplicationProvider.getApplicationContext(), extectedSize)
            }

            override fun describeTo(description: Description) {
                description.appendText("with text size: ")
                description.appendValue(extectedSize)
                description.appendText(". real size: ")
                description.appendValue(realData)
            }
        }
    }
}
package net.lachlanmckee.linkcleaner.testing.util

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not

object TextEspressoUtil {
  fun checkViewWithTextIsVisible(text: String) {
    onView(withText(text)).check(matches(isCompletelyDisplayed()))
  }

  fun checkViewWithTextIsNotVisible(text: String) {
    onView(withText(text)).check(matches(not(isDisplayed())))
  }
}

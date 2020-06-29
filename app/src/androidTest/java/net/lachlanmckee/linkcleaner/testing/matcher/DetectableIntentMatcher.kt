package net.lachlanmckee.linkcleaner.testing.matcher

import android.content.Intent
import junit.framework.TestCase.assertTrue
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class DetectableIntentMatcher(
    private val wrappedMatcher: Matcher<Intent>
) : TypeSafeMatcher<Intent>() {

    private var wasTriggered: Boolean = false

    fun assertIntentTriggered() {
        assertTrue("Expected Intent was not triggered", wasTriggered)
    }

    override fun describeTo(description: Description) {
        description.appendText("DetectableIntentMatcher [${wrappedMatcher.describeTo(description)}]")
    }

    public override fun matchesSafely(intent: Intent): Boolean {
        val matches = wrappedMatcher.matches(intent)
        if (matches) {
            wasTriggered = true
        }
        return matches
    }
}

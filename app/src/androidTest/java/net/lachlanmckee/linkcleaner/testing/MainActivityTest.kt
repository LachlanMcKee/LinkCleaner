package net.lachlanmckee.linkcleaner.testing

import android.app.Instrumentation
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import net.lachlanmckee.linkcleaner.feature.home.view.HomeFragment
import net.lachlanmckee.linkcleaner.testing.matcher.DetectableIntentMatcher
import net.lachlanmckee.linkcleaner.testing.util.TextEspressoUtil.checkViewWithTextIsNotVisible
import net.lachlanmckee.linkcleaner.testing.util.TextEspressoUtil.checkViewWithTextIsVisible
import net.lachlanmckee.linkcleaner.testing.util.launchFragmentInHiltContainer
import org.junit.After
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {

  @Before
  fun setup() {
    Intents.init()

    // Prepare the clipboard manager for testing (without this, there is an issue with handlers)
    InstrumentationRegistry.getInstrumentation().runOnMainSync {
      getClipboardManager()
    }

    setClipboardData("", "")
  }

  @After
  fun tearDown() {
    Intents.release()
  }

  @Test
  fun givenValidLinkCopiedBeforeLaunch_whenLaunch_thenExpectCopyLink() {
    setClipboardData("Cleaned Link", "http://www.example.com?key1=value1&key2=value2")

    launchFragmentInHiltContainer<HomeFragment>()
    assertLaunchChrome()
  }

  @Test
  fun givenLaunch_whenValidLinkCopied_thenExpectCopyLink() {
    launchFragmentInHiltContainer<HomeFragment>()

    checkViewWithTextIsNotVisible("Copy link and launch Chrome")

    setClipboardData("Cleaned Link", "http://www.example.com?key1=value1&key2=value2")

    // Give the system a moment to listen to the clipboard
    SystemClock.sleep(300)

    assertLaunchChrome()
  }

  @Test
  fun givenLaunch_whenNoLinksCopied_thenExpectNoCopyLinkButton() {
    launchFragmentInHiltContainer<HomeFragment>()

    checkViewWithTextIsNotVisible("Copy link and launch Chrome")
  }

  private fun assertLaunchChrome() {
    checkViewWithTextIsVisible("http://www.example.com/?key1=value1&key2=value2")
    checkViewWithTextIsVisible("http://www.example.com/")
    checkViewWithTextIsVisible("Copy link and launch Chrome")

    val detectableIntentMatcher = DetectableIntentMatcher(toPackage("com.android.chrome"))
    intending(detectableIntentMatcher)
      .respondWith(Instrumentation.ActivityResult(0, null))

    onView(withText("Copy link and launch Chrome")).perform(click())

    assertEquals("http://www.example.com/", getClipboardText())

    detectableIntentMatcher.assertIntentTriggered()
  }

  private fun setClipboardData(label: String, text: String) {
    getClipboardManager().setPrimaryClip(ClipData.newPlainText(label, text))
  }

  private fun getClipboardText(): String {
    return getClipboardManager().primaryClip!!.getItemAt(0)!!.text.toString()
  }

  private fun getClipboardManager(): ClipboardManager {
    return InstrumentationRegistry
      .getInstrumentation()
      .targetContext
      .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
  }
}

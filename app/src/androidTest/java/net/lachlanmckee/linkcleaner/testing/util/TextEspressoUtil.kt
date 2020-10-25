package net.lachlanmckee.linkcleaner.testing.util

import androidx.ui.test.assertIsDisplayed
import androidx.ui.test.onNodeWithText

object TextEspressoUtil {
  fun checkViewWithTextIsVisible(text: String) {
    onNodeWithText(text).assertIsDisplayed()
  }

  fun checkViewWithTextIsNotVisible(text: String) {
    onNodeWithText(text).assertDoesNotExist()
  }
}

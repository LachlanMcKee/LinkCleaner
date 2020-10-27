package net.lachlanmckee.linkcleaner.service.repository

import android.content.ClipData
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ClipboardManager {
  val clipText: String?

  fun addClipChangedListener(clipboardListener: () -> Unit)
  fun removeClipChangedListener(clipboardListener: () -> Unit)
  fun setClipText(label: String, content: String)
}

class AndroidClipboardManager @Inject constructor(
  @ApplicationContext private val context: Context
) : ClipboardManager {

  private val clipboardManager: android.content.ClipboardManager by lazy {
    context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
  }

  override val clipText: String?
    get() {
      return clipboardManager
        .primaryClip
        ?.getItemAt(0)
        ?.text
        ?.toString()
    }

  override fun addClipChangedListener(clipboardListener: () -> Unit) {
    clipboardManager.addPrimaryClipChangedListener(clipboardListener)
  }

  override fun removeClipChangedListener(clipboardListener: () -> Unit) {
    clipboardManager.removePrimaryClipChangedListener(clipboardListener)
  }

  override fun setClipText(label: String, content: String) {
    clipboardManager.setPrimaryClip(
      ClipData.newPlainText(label, content)
    )
  }

}

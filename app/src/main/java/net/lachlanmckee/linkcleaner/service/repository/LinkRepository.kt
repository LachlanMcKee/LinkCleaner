package net.lachlanmckee.linkcleaner.service.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import net.lachlanmckee.linkcleaner.service.model.LinkData
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import timber.log.Timber
import javax.inject.Inject

interface LinkRepository {
  fun getLink(): Flow<LinkData?>
  fun updateLink(linkData: LinkData)
}

class LinkRepositoryImpl @Inject constructor(
  private val clipboardManager: ClipboardManager
) : LinkRepository {

  @ExperimentalCoroutinesApi
  override fun getLink(): Flow<LinkData?> {
    return callbackFlow {
      val initialLinkData = getClipboardLinkData()
      offer(initialLinkData)
      Timber.d("getLink. initialLinkData: $initialLinkData")

      val clipboardListener: () -> Unit = {
        val newLinkData = getClipboardLinkData()
        Timber.d("getLink. newLinkData: $newLinkData")
        offer(newLinkData)
      }
      clipboardManager.addClipChangedListener(clipboardListener)
      awaitClose { clipboardManager.removeClipChangedListener(clipboardListener) }
    }
  }

  private fun getClipboardLinkData(): LinkData? {
    val originalHttpUrl = clipboardManager.clipText?.toHttpUrlOrNull()

    return originalHttpUrl?.let { httpUrl ->
      val replacementHttpUrl = HttpUrl.Builder()
        .scheme(httpUrl.scheme)
        .host(httpUrl.host)
        .port(httpUrl.port)
        .encodedPath(httpUrl.encodedPath)
        .build()

      LinkData(httpUrl, replacementHttpUrl)
    }
  }

  override fun updateLink(linkData: LinkData) {
    clipboardManager.setClipText(
      "Cleaned Link",
      linkData.replacementHttpUrl.toString()
    )
  }
}

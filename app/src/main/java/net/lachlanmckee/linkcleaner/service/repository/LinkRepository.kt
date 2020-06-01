package net.lachlanmckee.linkcleaner.service.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import javax.inject.Inject

interface LinkRepository {
    fun getLink(): Flow<HttpUrl?>
    fun updateLink(httpUrl: HttpUrl)
}

class LinkRepositoryImpl @Inject constructor(private val context: Context) : LinkRepository {

    private val clipboardManager: ClipboardManager by lazy {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    @ExperimentalCoroutinesApi
    override fun getLink(): Flow<HttpUrl?> {
        return callbackFlow {
            offer(getClipboardLink())

            val clipboardListener: () -> Unit = {
                offer(getClipboardLink())
            }
            clipboardManager.addPrimaryClipChangedListener(clipboardListener)
            awaitClose { clipboardManager.removePrimaryClipChangedListener(clipboardListener) }
        }
    }

    private fun getClipboardLink(): HttpUrl? {
        return clipboardManager
            .primaryClip
            ?.getItemAt(0)
            ?.text
            ?.toString()
            ?.toHttpUrlOrNull()
    }

    override fun updateLink(httpUrl: HttpUrl) {
        clipboardManager.setPrimaryClip(
            ClipData.newPlainText(
                "Cleaned Link",
                HttpUrl.Builder()
                    .scheme(httpUrl.scheme)
                    .host(httpUrl.host)
                    .port(httpUrl.port)
                    .encodedPath(httpUrl.encodedPath)
                    .build()
                    .toString()
            )
        )
    }
}

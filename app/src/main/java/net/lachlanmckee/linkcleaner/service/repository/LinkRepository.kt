package net.lachlanmckee.linkcleaner.service.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.util.*
import javax.inject.Inject

interface LinkRepository {
    fun getLink(): Flow<Optional<HttpUrl>>
    fun updateLink(httpUrl: HttpUrl)
}

class LinkRepositoryImpl @Inject constructor(private val context: Context) : LinkRepository {

    private val clipboardManager: ClipboardManager by lazy {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun getLink(): Flow<Optional<HttpUrl>> {
        return flow {
            emit(Optional.ofNullable(getCurrentLink()))
        }
    }

    private fun getCurrentLink(): HttpUrl? {
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

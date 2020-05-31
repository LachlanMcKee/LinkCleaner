package net.lachlanmckee.linkcleaner.feature.home

import android.content.*
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import dagger.android.support.AndroidSupportInjection
import net.lachlanmckee.linkcleaner.AbstractBindingFragment
import net.lachlanmckee.linkcleaner.databinding.HomeBinding
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

class HomeFragment : AbstractBindingFragment<HomeBinding>() {

    private val clipboardManager: ClipboardManager by lazy {
        requireContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
    }

    override fun onResume() {
        super.onResume()

        val context = requireContext()
        val httpUrl: HttpUrl? = getUrlFromClipboard()

        binding.homeLaunch.apply {
            isVisible = httpUrl != null
            setOnClickListener {
                if (httpUrl != null) {
                    updateClipboard(httpUrl)
                    launchChrome(context)
                }
            }
        }
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): HomeBinding {
        return HomeBinding.inflate(inflater, container, false)
    }

    private fun getUrlFromClipboard(): HttpUrl? {
        return clipboardManager
            .primaryClip
            ?.getItemAt(0)
            ?.text
            ?.toString()
            ?.toHttpUrlOrNull()
    }

    private fun updateClipboard(httpUrl: HttpUrl) {
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

    private fun launchChrome(context: Context) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")
        try {
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            // Chrome browser presumably not installed so allow user to choose instead
            intent.setPackage(null)
            context.startActivity(intent)
        }
    }
}

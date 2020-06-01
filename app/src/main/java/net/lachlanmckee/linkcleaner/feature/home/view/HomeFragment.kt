package net.lachlanmckee.linkcleaner.feature.home.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.android.support.AndroidSupportInjection
import net.lachlanmckee.linkcleaner.AbstractBindingFragment
import net.lachlanmckee.linkcleaner.databinding.HomeBinding
import net.lachlanmckee.linkcleaner.di.viewmodel.ViewModelProviderFactory
import net.lachlanmckee.linkcleaner.feature.home.viewmodel.HomeViewModel
import okhttp3.HttpUrl
import java.util.*
import javax.inject.Inject

class HomeFragment : AbstractBindingFragment<HomeBinding>() {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val model: HomeViewModel by viewModels { viewModelProviderFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
    }

    override fun onResume() {
        super.onResume()

        model.httpUrl.observe(this, Observer { httpUrl: Optional<HttpUrl> ->
            binding.homeLaunch.apply {
                isVisible = httpUrl.isPresent
                setOnClickListener {
                    if (httpUrl.isPresent) {
                        model.updateLink()
                        launchChrome()
                    }
                }
            }
        })
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): HomeBinding {
        return HomeBinding.inflate(inflater, container, false)
    }

    private fun launchChrome() {
        val context = requireContext()
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

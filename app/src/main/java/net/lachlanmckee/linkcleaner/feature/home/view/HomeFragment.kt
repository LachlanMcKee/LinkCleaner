package net.lachlanmckee.linkcleaner.feature.home.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import net.lachlanmckee.linkcleaner.AbstractBindingFragment
import net.lachlanmckee.linkcleaner.databinding.HomeBinding
import net.lachlanmckee.linkcleaner.di.viewmodel.ViewModelProviderFactory
import net.lachlanmckee.linkcleaner.feature.home.viewmodel.HomeViewModel
import net.lachlanmckee.linkcleaner.feature.home.viewmodel.State
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : AbstractBindingFragment<HomeBinding>() {
  @Inject
  lateinit var viewModelProviderFactory: ViewModelProviderFactory

  private val model: HomeViewModel by viewModels { viewModelProviderFactory }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val hasWindowFocus = view.hasWindowFocus()
    Timber.d("onViewCreated. hasWindowFocus: $hasWindowFocus")
    if (hasWindowFocus) {
      listenToState()
    } else {
      view.viewTreeObserver.addOnWindowFocusChangeListener(object :
          ViewTreeObserver.OnWindowFocusChangeListener {
          override fun onWindowFocusChanged(hasFocus: Boolean) {
            if (hasFocus) {
              listenToState()
              view.viewTreeObserver.removeOnWindowFocusChangeListener(this)
            }
          }
        })
    }
  }

  override fun onDestroyView() {
    Timber.d("onDestroyView")
    super.onDestroyView()
  }

  private fun listenToState() {
    Timber.d("listenToState. observe")
    model.state.observe(
      viewLifecycleOwner,
      Observer { state: State ->
        Timber.d("listenToState. State: $state")
        when (state) {
          State.Empty -> {
            binding.homeLaunch.isVisible = false
            binding.homeOriginalUrl.isVisible = false
            binding.homeReplacementUrl.isVisible = false
          }
          is State.LinkFound -> {
            binding.homeLaunch.apply {
              isVisible = true
              setOnClickListener {
                model.updateLink()
                launchChrome()
              }
            }
            binding.homeOriginalUrl.isVisible = true
            binding.homeReplacementUrl.isVisible = true
            binding.homeOriginalUrl.text = state.originalUrl
            binding.homeReplacementUrl.text = state.replacementUrl
          }
        }
      }
    )
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

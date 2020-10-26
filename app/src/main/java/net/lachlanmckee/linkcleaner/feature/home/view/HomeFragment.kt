package net.lachlanmckee.linkcleaner.feature.home.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.lachlanmckee.linkcleaner.di.viewmodel.ViewModelProviderFactory
import net.lachlanmckee.linkcleaner.feature.home.viewmodel.HomeViewModel
import net.lachlanmckee.linkcleaner.feature.home.viewmodel.State
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
  @Inject
  lateinit var viewModelProviderFactory: ViewModelProviderFactory

  private val model: HomeViewModel by viewModels { viewModelProviderFactory }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return ComposeView(requireContext()).apply {
      setContent {
        screen()
      }
    }
  }

  @Composable
  private fun screen() {
    model.state.observeAsState(State.Empty).value.let { state ->
      when (state) {
        State.Empty -> {
          noLinkFoundState()
        }
        is State.LinkFound -> {
          linkFoundState(state)
        }
      }
    }
  }

  @Composable
  private fun noLinkFoundState() {
  }

  @Composable
  private fun linkFoundState(state: State.LinkFound) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      urlText(state.originalUrl)
      urlText(state.replacementUrl)
      Button(
        onClick = {
          model.updateLink()
          launchChrome()
        },
        content = { Text("Copy link and launch Chrome") }
      )
    }
  }

  @Composable
  private fun urlText(text: String) {
    Text(
      text = text,
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    )
  }

  override fun onDestroyView() {
    Timber.d("onDestroyView")
    super.onDestroyView()
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

package net.lachlanmckee.linkcleaner.feature.settings.view

import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import net.lachlanmckee.linkcleaner.AbstractBindingFragment
import net.lachlanmckee.linkcleaner.databinding.SettingsBinding

@AndroidEntryPoint
class SettingsFragment : AbstractBindingFragment<SettingsBinding>() {
  override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): SettingsBinding {
    return SettingsBinding.inflate(inflater, container, false)
  }
}

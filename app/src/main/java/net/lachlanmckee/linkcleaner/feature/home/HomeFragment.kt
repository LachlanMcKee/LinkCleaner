package net.lachlanmckee.linkcleaner.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import net.lachlanmckee.linkcleaner.AbstractBindingFragment
import net.lachlanmckee.linkcleaner.databinding.HomeBinding

class HomeFragment : AbstractBindingFragment<HomeBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): HomeBinding {
        return HomeBinding.inflate(inflater, container, false)
    }
}

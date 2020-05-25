package net.lachlanmckee.linkcleaner

import android.view.LayoutInflater
import android.view.ViewGroup
import net.lachlanmckee.linkcleaner.databinding.HomeBinding

class HomeFragment : AbstractBindingFragment<HomeBinding>() {
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): HomeBinding {
        return HomeBinding.inflate(inflater, container, false)
    }
}

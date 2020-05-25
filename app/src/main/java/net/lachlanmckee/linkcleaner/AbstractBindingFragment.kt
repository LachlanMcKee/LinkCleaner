package net.lachlanmckee.linkcleaner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class AbstractBindingFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    private val binding: T
        get() = requireNotNull(_binding) { "Binding was accessed after view destroyed" }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
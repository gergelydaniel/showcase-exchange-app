package com.danielgergely.showcase.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


abstract class BaseFragment<T : ViewBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: T? = null

    protected val binding: T
        get() = _binding ?: throw IllegalStateException(
            "binding accessed before onCreateView or after onDestroyView"
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = this.inflater(inflater, container, false)
        _binding = binding
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

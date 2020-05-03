package com.danielgergely.showcase.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val viewModel: BaseViewModel

    private var bindingInflater: ((LayoutInflater, ViewGroup?, Boolean) -> ViewBinding)? = null
    private var _binding: ViewBinding? = null

    private inner class BindingDelegate<VB : ViewBinding> : ReadOnlyProperty<BaseFragment, VB> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: BaseFragment, property: KProperty<*>): VB {
            return _binding as? VB
                ?: throw IllegalStateException("binding accessed before onCreateView or after onDestroyView")
        }
    }

    protected fun <VB : ViewBinding> viewBinding(
        inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    ): ReadOnlyProperty<BaseFragment, VB> {
        bindingInflater = inflater
        return BindingDelegate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = bindingInflater?.let { bindingInflater ->
        bindingInflater(inflater, container, false).also { _binding = it }.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun <T> LiveData<T>.observe(observer: (T) -> Any?) =
        observe(viewLifecycleOwner, Observer { observer(it) })
}

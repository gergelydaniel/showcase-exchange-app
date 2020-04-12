package com.danielgergely.showcase.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class ViewModelDelegate<VM : ViewModel>(
    private val viewModelClass: Class<VM>
) : ReadOnlyProperty<BaseFragment<*>, VM> {

    private var viewModel: VM? = null

    override fun getValue(thisRef: BaseFragment<*>, property: KProperty<*>) =
        viewModel ?: ViewModelProvider(thisRef, thisRef.viewModelFactory)
            .get(viewModelClass).also {
                viewModel = it
            }
}

inline fun <reified VM : ViewModel> viewModel() = ViewModelDelegate(VM::class.java)

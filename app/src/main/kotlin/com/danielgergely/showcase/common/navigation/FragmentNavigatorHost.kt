package com.danielgergely.showcase.common.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData


interface FragmentNavigatorHost {

    val state: LiveData<NavigatorState>

    fun inject(@IdRes containerViewId: Int, fragmentManager: FragmentManager)
}

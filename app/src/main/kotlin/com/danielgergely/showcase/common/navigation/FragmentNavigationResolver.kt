package com.danielgergely.showcase.common.navigation

import com.danielgergely.showcase.common.BaseFragment


interface FragmentNavigationResolver<NavigationKey> {

    val homeFragment: () -> BaseFragment

    fun createFragmentByKey(key: NavigationKey): BaseFragment
}

package com.danielgergely.showcase.common.navigation

import com.danielgergely.showcase.common.BaseFragment


data class NavigatorState(
    val activeFragment: BaseFragment,
    val canGoBack: Boolean
)

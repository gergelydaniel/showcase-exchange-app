package com.danielgergely.showcase

import com.danielgergely.showcase.AppNavigationKey.DASHBOARD
import com.danielgergely.showcase.AppNavigationKey.EXCHANGE
import com.danielgergely.showcase.common.navigation.FragmentNavigationResolver
import com.danielgergely.showcase.dashboard.ui.DashboardFragment
import com.danielgergely.showcase.exchange.ui.ExchangeFragment

enum class AppNavigationKey {
    DASHBOARD, EXCHANGE
}

class NavigationConfig : FragmentNavigationResolver<AppNavigationKey> {
    override val homeFragment = { DashboardFragment() }

    override fun createFragmentByKey(key: AppNavigationKey) = when (key) {
        DASHBOARD -> DashboardFragment()
        EXCHANGE -> ExchangeFragment()
    }
}

package com.danielgergely.showcase.common.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import com.danielgergely.showcase.common.BaseFragment
import com.danielgergely.showcase.common.removeLastElement


/**
 * This navigator is terrible, please don't look at it
 */
class FragmentNavigator<NavigationKey>(
    private val resolver: FragmentNavigationResolver<NavigationKey>
) : Navigator<NavigationKey>, FragmentNavigatorHost {

    private var containerViewId: Int = 0
    override val state = MutableLiveData<NavigatorState>()

    private lateinit var fragmentManager: FragmentManager

    private val backStack = mutableListOf<BaseFragment>()

    override fun navigateBack(): Boolean {
        return if (backStack.size <= 1) {
            false
        } else {
            backStack.removeLastElement()
            setFragment(backStack.last())
            fireStateChangeEvent()
            true
        }
    }

    override fun navigateTo(key: NavigationKey) {
        val fragment = resolver.createFragmentByKey(key)

        backStack.add(fragment)
        setFragment(fragment)
    }

    override fun inject(@IdRes containerViewId: Int, fragmentManager: FragmentManager) {
        this.containerViewId = containerViewId
        this.fragmentManager = fragmentManager

        if (backStack.isEmpty()) {
            val homeFragment = resolver.homeFragment()
            backStack.add(homeFragment)
            setFragment(homeFragment)
        }
    }

    private fun setFragment(fragment: Fragment) = fragmentManager.commit {
        replace(containerViewId, fragment)
        runOnCommit(::fireStateChangeEvent)
    }

    private fun fireStateChangeEvent() {
        state.value = NavigatorState(
            activeFragment = backStack.last(),
            canGoBack = backStack.size > 1
        )
    }
}

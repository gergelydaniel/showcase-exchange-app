package com.danielgergely.showcase.dashboard.ui

import androidx.lifecycle.MutableLiveData
import com.danielgergely.showcase.AppNavigationKey
import com.danielgergely.showcase.AppNavigationKey.EXCHANGE
import com.danielgergely.showcase.common.BaseViewModel
import com.danielgergely.showcase.common.di.AppNavigation
import com.danielgergely.showcase.common.navigation.Navigator
import javax.inject.Inject


class DashboardViewModel @Inject constructor(
    @AppNavigation private val navigator: Navigator<AppNavigationKey>
) : BaseViewModel() {

    override val progressBarDisplayed = MutableLiveData(false)
    override val title = MutableLiveData("Dashboard")

    fun exchangeButtonClicked() = navigator.navigateTo(EXCHANGE)
}

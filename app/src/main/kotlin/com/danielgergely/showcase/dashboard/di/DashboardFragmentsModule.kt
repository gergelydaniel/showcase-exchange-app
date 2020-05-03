package com.danielgergely.showcase.dashboard.di

import com.danielgergely.showcase.dashboard.ui.DashboardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class DashboardFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeExchangeFragmentInjector(): DashboardFragment
}

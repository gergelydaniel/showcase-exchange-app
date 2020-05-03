package com.danielgergely.showcase.exchange.di

import com.danielgergely.showcase.exchange.ui.ExchangeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ExchangeFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeExchangeFragmentInjector(): ExchangeFragment
}

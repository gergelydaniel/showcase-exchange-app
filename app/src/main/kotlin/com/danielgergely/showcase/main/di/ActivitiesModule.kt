package com.danielgergely.showcase.main.di

import com.danielgergely.showcase.main.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity
}

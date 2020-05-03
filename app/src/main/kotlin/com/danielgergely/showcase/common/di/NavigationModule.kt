package com.danielgergely.showcase.common.di

import com.danielgergely.showcase.AppNavigationKey
import com.danielgergely.showcase.NavigationConfig
import com.danielgergely.showcase.common.navigation.FragmentNavigationResolver
import com.danielgergely.showcase.common.navigation.FragmentNavigator
import com.danielgergely.showcase.common.navigation.FragmentNavigatorHost
import com.danielgergely.showcase.common.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @AppNavigation
    @Singleton
    fun fragmentNavigationResolver(): FragmentNavigationResolver<AppNavigationKey> =
        NavigationConfig()

    @Provides
    @AppNavigation
    @Singleton
    fun fragmentNavigator(
        @AppNavigation resolver: FragmentNavigationResolver<AppNavigationKey>
    ): FragmentNavigator<AppNavigationKey> = FragmentNavigator(resolver)

    @Provides
    @AppNavigation
    @Singleton
    fun navigator(
        @AppNavigation fragmentNavigator: FragmentNavigator<AppNavigationKey>
    ): Navigator<AppNavigationKey> = fragmentNavigator

    @Provides
    @AppNavigation
    @Singleton
    fun fragmentNavigatorHost(
        @AppNavigation fragmentNavigator: FragmentNavigator<AppNavigationKey>
    ): FragmentNavigatorHost = fragmentNavigator
}

@Qualifier
annotation class AppNavigation

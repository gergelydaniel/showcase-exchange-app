package com.danielgergely.showcase.common

import androidx.lifecycle.ViewModelProvider
import com.danielgergely.showcase.common.di.DaggerViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}

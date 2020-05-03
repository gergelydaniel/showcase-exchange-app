package com.danielgergely.showcase.dashboard.di

import androidx.lifecycle.ViewModel
import com.danielgergely.showcase.common.di.ViewModelKey
import com.danielgergely.showcase.dashboard.ui.DashboardViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DashboardViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bind(viewModel: DashboardViewModel): ViewModel
}

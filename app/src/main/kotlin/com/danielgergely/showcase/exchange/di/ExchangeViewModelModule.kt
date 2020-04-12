package com.danielgergely.showcase.exchange.di

import androidx.lifecycle.ViewModel
import com.danielgergely.showcase.common.di.ViewModelKey
import com.danielgergely.showcase.exchange.ui.ExchangeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ExchangeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExchangeViewModel::class)
    abstract fun bind(viewModel: ExchangeViewModel): ViewModel
}

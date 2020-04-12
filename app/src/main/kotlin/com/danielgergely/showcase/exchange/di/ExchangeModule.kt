package com.danielgergely.showcase.exchange.di

import com.danielgergely.showcase.common.di.IoScheduler
import com.danielgergely.showcase.exchange.api.ExchangeRatesApi
import com.danielgergely.showcase.exchange.data.ExchangeRepositoryImpl
import com.danielgergely.showcase.exchange.di.qualifiers.ConfigBaseSymbol
import com.danielgergely.showcase.exchange.domain.ExchangeRepository
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton


@Module
class ExchangeModule {

    @Provides
    @Singleton
    fun exchangeRepository(
        exchangeRatesApi: ExchangeRatesApi,
        @ConfigBaseSymbol baseSymbol: String,
        @IoScheduler ioScheduler: Scheduler
    ): ExchangeRepository =
        ExchangeRepositoryImpl(exchangeRatesApi, baseSymbol, ioScheduler)
}

package com.danielgergely.showcase.exchange.di

import com.danielgergely.showcase.Config
import com.danielgergely.showcase.exchange.di.qualifiers.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ConfigModule {

    @Provides
    @Singleton
    @ConfigDataValidity
    fun dataValidity() = Config.dataValidity

    @Provides
    @Singleton
    @ConfigPersisterFilename
    fun persisterFilename() = Config.persisterFileName

    @Provides
    @Singleton
    @ConfigExchangeRatesApiBaseUrl
    fun exchangeRatesApiBaseUrl() = Config.exchangeRatesApiBaseUrl

    @Provides
    @Singleton
    @ConfigBaseSymbol
    fun baseSymbol() = Config.baseSymbol

    @Provides
    @Singleton
    @ConfigCurrentSymbol
    fun currentSymbol() = Config.currentSymbol
}

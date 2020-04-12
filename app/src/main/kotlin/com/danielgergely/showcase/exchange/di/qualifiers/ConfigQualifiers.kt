package com.danielgergely.showcase.exchange.di.qualifiers

import javax.inject.Qualifier


@Qualifier
annotation class ConfigDataValidity

@Qualifier
annotation class ConfigPersisterFilename

@Qualifier
annotation class ConfigExchangeRatesApiBaseUrl

@Qualifier
annotation class ConfigBaseSymbol

@Qualifier
annotation class ConfigCurrentSymbol

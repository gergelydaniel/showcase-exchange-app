package com.danielgergely.showcase.exchange

import com.danielgergely.showcase.common.minutes


object Config {
    const val exchangeRatesApiBaseUrl = "https://api.exchangeratesapi.io/"
    const val baseSymbol = "EUR"
    const val currentSymbol = "HUF"

    const val persisterFileName = "cache.json"
    val dataValidity = 1.minutes
}

package com.danielgergely.showcase.exchange.ui


sealed class ExchangeViewState {

    object Progress : ExchangeViewState()

    data class Content(
        val exchangeRateLabel: String,
        val dateLabel: String
    ) : ExchangeViewState()

    data class Error(val text: String) : ExchangeViewState()
}

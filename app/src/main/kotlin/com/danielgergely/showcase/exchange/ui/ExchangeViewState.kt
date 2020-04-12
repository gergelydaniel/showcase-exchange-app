package com.danielgergely.showcase.exchange.ui

sealed class ExchangeViewState {

    object Progress : ExchangeViewState()

    data class Content(
        val exchangeRate: String,
        val date: String
    ) : ExchangeViewState()

    data class Error(val text: String) : ExchangeViewState()
}

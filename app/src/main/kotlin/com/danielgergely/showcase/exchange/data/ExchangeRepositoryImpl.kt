package com.danielgergely.showcase.exchange.data

import com.danielgergely.showcase.exchange.api.ExchangeRatesApi
import com.danielgergely.showcase.exchange.domain.ExchangeRepository
import com.danielgergely.showcase.exchange.domain.model.ExchangeData
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit


class ExchangeRepositoryImpl(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val baseSymbol: String,
    private val ioScheduler: Scheduler
) : ExchangeRepository {

    override fun getData(): Single<ExchangeData> =
        exchangeRatesApi.getLatest(baseSymbol)
            .subscribeOn(ioScheduler)
            .delay(2, TimeUnit.SECONDS)
            .map { it.toDomain() }

}

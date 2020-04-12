package com.danielgergely.showcase.exchange.data

import com.danielgergely.showcase.common.util.Option
import com.danielgergely.showcase.common.util.Option.Empty
import com.danielgergely.showcase.common.util.Option.Some
import com.danielgergely.showcase.exchange.api.ExchangeRatesApi
import com.danielgergely.showcase.exchange.domain.ExchangeRepository
import com.danielgergely.showcase.exchange.domain.model.ExchangeData
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


class ExchangeRepositoryImpl(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val baseSymbol: String,
    private val ioScheduler: Scheduler
) : ExchangeRepository {

    private val cache = BehaviorSubject.createDefault<Option<ExchangeData>>(Empty)

    override fun getData(): Single<ExchangeData> =
        cache
            .firstElement()
            .ofType(Some::class.java)
            .map { it.value as ExchangeData }
            .switchIfEmpty(Single.defer {
                exchangeRatesApi.getLatest(baseSymbol)
                    .subscribeOn(ioScheduler)
                    .delay(2, TimeUnit.SECONDS)
                    .map { it.toDomain() }
                    .doOnSuccess { cache.onNext(Some(it)) }
            })

    override fun clearCache() = cache.onNext(Empty)
}

package com.danielgergely.showcase.exchange.domain

import com.danielgergely.showcase.exchange.domain.model.ExchangeData
import io.reactivex.rxjava3.core.Single


interface ExchangeRepository {

    fun getData(): Single<ExchangeData>
}

package com.danielgergely.showcase.exchange.data

import com.danielgergely.showcase.exchange.api.dto.LatestResponse
import com.danielgergely.showcase.exchange.domain.model.ExchangeData


fun LatestResponse.toDomain() = ExchangeData(
    eurToHuf = rates.huf,
    date = date
)

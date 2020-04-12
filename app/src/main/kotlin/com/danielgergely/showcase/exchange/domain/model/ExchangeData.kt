package com.danielgergely.showcase.exchange.domain.model

import org.threeten.bp.LocalDate
import java.math.BigDecimal


data class ExchangeData(
    val eurToHuf: BigDecimal,
    val date: LocalDate
)

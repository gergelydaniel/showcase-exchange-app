package com.danielgergely.showcase.exchange.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class Rates(
    @Json(name = "HUF") val huf: BigDecimal
)

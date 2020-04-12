package com.danielgergely.showcase.exchange.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate

@JsonClass(generateAdapter = true)
data class LatestResponse(
    @Json(name = "rates") val rates: Rates,
    @Json(name = "date") val date: LocalDate
)

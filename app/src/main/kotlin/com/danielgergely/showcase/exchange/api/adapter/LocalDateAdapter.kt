package com.danielgergely.showcase.exchange.api.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


object LocalDateAdapter {

    private val formatter = DateTimeFormatter.ISO_DATE

    @FromJson
    fun fromJson(string: String): LocalDate = LocalDate.parse(string, formatter)

    @ToJson
    fun toJson(zonedDateTime: LocalDate): String = formatter.format(zonedDateTime)
}

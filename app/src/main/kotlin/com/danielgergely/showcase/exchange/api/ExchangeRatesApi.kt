package com.danielgergely.showcase.exchange.api

import com.danielgergely.showcase.exchange.api.dto.LatestResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ExchangeRatesApi {

    @GET("latest")
    fun getLatest(@Query("base") base: String): Single<LatestResponse>
}

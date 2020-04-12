package com.danielgergely.showcase.exchange.di

import com.danielgergely.showcase.exchange.api.ExchangeRatesApi
import com.danielgergely.showcase.exchange.api.adapter.BigDecimalAdapter
import com.danielgergely.showcase.exchange.api.adapter.LocalDateAdapter
import com.danielgergely.showcase.exchange.di.qualifiers.ConfigExchangeRatesApiBaseUrl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi
        .Builder()
        .add(LocalDateAdapter)
        .add(BigDecimalAdapter)
        .build()

    @Singleton
    @Provides
    fun okHttpClient() = OkHttpClient()

    @Singleton
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @ConfigExchangeRatesApiBaseUrl baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun exchangeRatesApi(retrofit: Retrofit): ExchangeRatesApi =
        retrofit.create(ExchangeRatesApi::class.java)
}

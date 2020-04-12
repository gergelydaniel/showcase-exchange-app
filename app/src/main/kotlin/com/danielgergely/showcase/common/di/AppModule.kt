package com.danielgergely.showcase.common.di

import android.content.Context
import com.danielgergely.showcase.ExchangeApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: ExchangeApp): Context = app.applicationContext
}

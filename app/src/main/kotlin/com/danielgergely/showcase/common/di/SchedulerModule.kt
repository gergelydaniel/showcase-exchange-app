package com.danielgergely.showcase.common.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers


@Module
class SchedulerModule {

    @Provides
    @IoScheduler
    fun ioScheduler(): Scheduler = Schedulers.io()

    @Provides
    @UiScheduler
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}

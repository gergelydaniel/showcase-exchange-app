package com.danielgergely.showcase.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable


fun <T> Observable<T>.toLiveDataObserverScoped() =
    LiveDataReactiveStreams.fromPublisher(this.toFlowable(BackpressureStrategy.LATEST))

fun <T, U> LiveData<T>.switchMap(function: (T) -> LiveData<U>): LiveData<U> =
    Transformations.switchMap(this, function)

fun <T, U> LiveData<T>.map(function: (T) -> U): LiveData<U> =
    Transformations.map(this, function)

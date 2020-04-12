package com.danielgergely.showcase.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Observer
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import org.threeten.bp.Duration
import org.threeten.bp.temporal.ChronoUnit.MINUTES

/**
 * Utility to force a when expression to be exhaustive, just by using its return value
 */
inline val Any?.exhaustive
    get() = this

fun <T> Observable<T>.toLiveData() = let {
    LiveDataReactiveStreams.fromPublisher(it.toFlowable(BackpressureStrategy.LATEST))
}

fun <T> Fragment.observe(liveData: LiveData<T>, block: (T) -> Any?) {
    liveData.observe(this, Observer { block(it) })
}

inline val Int.minutes: Duration
    get() = Duration.of(toLong(), MINUTES)

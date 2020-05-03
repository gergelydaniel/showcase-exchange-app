package com.danielgergely.showcase.common

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import org.threeten.bp.Duration
import org.threeten.bp.temporal.ChronoUnit.MINUTES

/**
 * Utility to force a when expression to be exhaustive, just by using its return value
 */
inline val Any?.exhaustive
    get() = this

var View.viewVisibility: Boolean
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

fun <T> MutableList<T>.removeLastElement(): T =
    if (isEmpty()) throw NoSuchElementException("List is empty.") else removeAt(lastIndex)

inline val Int.minutes: Duration
    get() = Duration.of(toLong(), MINUTES)

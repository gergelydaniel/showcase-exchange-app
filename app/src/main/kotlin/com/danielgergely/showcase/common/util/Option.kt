package com.danielgergely.showcase.common.util


sealed class Option<out T> {

    data class Some<T>(val value: T) : Option<T>()

    object Empty : Option<Nothing>()
}

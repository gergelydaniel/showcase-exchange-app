package com.danielgergely.showcase.common.navigation


interface Navigator<NavigationKey> {

    fun navigateBack(): Boolean

    fun navigateTo(key: NavigationKey)
}

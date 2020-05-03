package com.danielgergely.showcase.common

import androidx.lifecycle.LiveData


abstract class BaseViewModel : DisposableViewModel() {

    abstract val title: LiveData<String>
    abstract val progressBarDisplayed: LiveData<Boolean>
}

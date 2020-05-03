package com.danielgergely.showcase.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.atomic.AtomicBoolean

open class DisposableViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun <T> Observable<T>.toLiveDataViewModelScoped(): LiveData<T> {
        return object : LiveData<T>() {
            val subscribed = AtomicBoolean()

            override fun onActive() {
                if (subscribed.compareAndSet(false, true)) {
                    compositeDisposable.add(subscribe(::postValue))
                }
            }
        }
    }
}

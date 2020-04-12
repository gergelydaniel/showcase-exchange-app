package com.danielgergely.showcase.exchange.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.danielgergely.showcase.common.di.UiScheduler
import com.danielgergely.showcase.common.toLiveData
import com.danielgergely.showcase.exchange.domain.ExchangeRepository
import com.danielgergely.showcase.exchange.domain.model.ExchangeData
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import javax.inject.Inject


class ExchangeViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    @UiScheduler private val uiScheduler: Scheduler
) : ViewModel() {

    private val refreshSubject = BehaviorSubject.createDefault(Unit)

    private val dateFormatter = DateTimeFormatter.ofPattern("YYYY. MM. dd.")

    val state: LiveData<ExchangeViewState>
        get() =
            refreshSubject.switchMap {
                exchangeRepository.getData()
                    .toObservable()
                    .map(::mapToUiModel)
                    .startWithItem(ExchangeViewState.Progress)
                    .doOnError(Timber::e)
                    .onErrorReturn {
                        ExchangeViewState.Error(it.message ?: "")
                    }
                    .observeOn(uiScheduler)
            }.toLiveData()

    private fun mapToUiModel(data: ExchangeData): ExchangeViewState = ExchangeViewState.Content(
        exchangeRate = "1 EUR = ${data.eurToHuf} HUF",
        date = " - as of ${dateFormatter.format(data.date)}"
    )

    fun refresh() {
        exchangeRepository.clearCache()
        refreshSubject.onNext(Unit)
    }
}

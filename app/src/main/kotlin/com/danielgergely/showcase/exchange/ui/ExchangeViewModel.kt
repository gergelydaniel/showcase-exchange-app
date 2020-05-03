package com.danielgergely.showcase.exchange.ui

import androidx.lifecycle.LiveData
import com.danielgergely.showcase.common.BaseViewModel
import com.danielgergely.showcase.common.RefreshableViewModel
import com.danielgergely.showcase.common.di.UiScheduler
import com.danielgergely.showcase.common.map
import com.danielgergely.showcase.exchange.domain.ExchangeRepository
import com.danielgergely.showcase.exchange.domain.model.ExchangeData
import com.danielgergely.showcase.exchange.ui.ExchangeViewState.Progress
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.subjects.PublishSubject
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import javax.inject.Inject


class ExchangeViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    @UiScheduler private val uiScheduler: Scheduler
) : BaseViewModel(), RefreshableViewModel {

    private val refreshSubject = PublishSubject.create<Unit>()

    private val dateFormatter = DateTimeFormatter.ofPattern("YYYY. MM. dd.")

    val state: LiveData<ExchangeViewState> =
        exchangeRepository.getData()
            .toObservable()
            .map(::mapToUiModel)
            .startWithItem(Progress)
            .doOnError(Timber::e)
            .onErrorReturn { ExchangeViewState.Error(it.message ?: "") }
            .repeatWhen { refreshSubject }
            .toLiveDataViewModelScoped()

    override val progressBarDisplayed = state.map { it is Progress }

    override val title = state.map { state ->
        when (state) {
            is Progress -> "Loading..."
            else -> "EUR to HUF rates"
        }
    }

    override fun refresh() = refreshSubject.onNext(Unit)

    private fun mapToUiModel(data: ExchangeData): ExchangeViewState = ExchangeViewState.Content(
        exchangeRateLabel = "1 EUR = ${data.eurToHuf} HUF",
        dateLabel = " - as of ${dateFormatter.format(data.date)}"
    )
}

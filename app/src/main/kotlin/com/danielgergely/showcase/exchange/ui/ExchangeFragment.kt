package com.danielgergely.showcase.exchange.ui

import android.os.Bundle
import com.danielgergely.showcase.common.BaseFragment
import com.danielgergely.showcase.common.exhaustive
import com.danielgergely.showcase.common.viewModel
import com.danielgergely.showcase.common.viewVisibility
import com.danielgergely.showcase.exchange.databinding.FragmentExchangeBinding
import com.danielgergely.showcase.exchange.ui.ExchangeViewState.*


class ExchangeFragment : BaseFragment() {

    override val viewModel by viewModel<ExchangeViewModel>()
    private val binding by viewBinding(FragmentExchangeBinding::inflate)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.state.observe(::render)
    }

    private fun render(state: ExchangeViewState) = binding.apply {
        groupContent.viewVisibility = false
        textError.viewVisibility = false
        textLoading.viewVisibility = false

        when (state) {
            is Progress -> {
                textLoading.viewVisibility = true
            }

            is Content -> {
                groupContent.viewVisibility = true

                textExchangeRate.text = state.exchangeRateLabel
                textDate.text = state.dateLabel
            }

            is Error -> {
                textError.viewVisibility = true

                textError.text = state.text
            }
        }.exhaustive
    }
}

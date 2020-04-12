package com.danielgergely.showcase.exchange.ui

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.danielgergely.showcase.common.BaseFragment
import com.danielgergely.showcase.common.exhaustive
import com.danielgergely.showcase.common.observe
import com.danielgergely.showcase.common.viewModel
import com.danielgergely.showcase.exchange.databinding.FragmentExchangeBinding
import com.danielgergely.showcase.exchange.ui.ExchangeViewState.*


class ExchangeFragment : BaseFragment<FragmentExchangeBinding>(FragmentExchangeBinding::inflate) {

    private val viewModel by viewModel<ExchangeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeLayout.setOnRefreshListener(viewModel::refresh)
    }

    override fun onDestroyView() {
        binding.swipeLayout.setOnRefreshListener(null)

        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observe(viewModel.state, ::render)
    }

    private fun render(state: ExchangeViewState) = binding.apply {
        groupContent.visibility = GONE
        textError.visibility = GONE
        textLoading.visibility = GONE
        swipeLayout.isRefreshing = false

        when (state) {
            is Progress -> {
                textLoading.visibility = VISIBLE
                swipeLayout.isRefreshing = true
            }

            is Content -> {
                groupContent.visibility = VISIBLE

                textExchangeRate.text = state.exchangeRate
                textDate.text = state.date
            }

            is Error -> {
                textError.visibility = VISIBLE

                textError.text = state.text
            }
        }.exhaustive
    }
}

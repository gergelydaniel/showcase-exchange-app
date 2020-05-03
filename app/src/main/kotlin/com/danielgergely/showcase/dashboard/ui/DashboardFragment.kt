package com.danielgergely.showcase.dashboard.ui

import android.os.Bundle
import android.view.View
import com.danielgergely.showcase.common.BaseFragment
import com.danielgergely.showcase.common.viewModel
import com.danielgergely.showcase.exchange.databinding.FragmentDashboardBinding


class DashboardFragment : BaseFragment() {

    private val binding by viewBinding(FragmentDashboardBinding::inflate)
    override val viewModel by viewModel<DashboardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dashboardExchangeButton.setOnClickListener { viewModel.exchangeButtonClicked() }
    }
}

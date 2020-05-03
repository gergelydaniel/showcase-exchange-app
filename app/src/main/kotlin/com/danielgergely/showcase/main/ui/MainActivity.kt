package com.danielgergely.showcase.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.danielgergely.showcase.AppNavigationKey
import com.danielgergely.showcase.common.RefreshableViewModel
import com.danielgergely.showcase.common.di.AppNavigation
import com.danielgergely.showcase.common.map
import com.danielgergely.showcase.common.navigation.FragmentNavigatorHost
import com.danielgergely.showcase.common.navigation.Navigator
import com.danielgergely.showcase.common.switchMap
import com.danielgergely.showcase.common.viewVisibility
import com.danielgergely.showcase.exchange.R
import com.danielgergely.showcase.exchange.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    @AppNavigation
    lateinit var navigatorHost: FragmentNavigatorHost

    @Inject
    @AppNavigation
    lateinit var navigator: Navigator<AppNavigationKey>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).let { binding ->
            this.binding = binding
            setContentView(binding.root)
        }

        AndroidInjection.inject(this)

        navigatorHost.inject(R.id.main__fragment_container, supportFragmentManager)

        binding.mainToolbar.toolbarBackButton.setOnClickListener { navigator.navigateBack() }
        binding.swipeLayout.setOnRefreshListener {
            navigatorHost.state.value?.activeFragment?.viewModel?.let { baseViewModel ->
                (baseViewModel as? RefreshableViewModel)?.refresh()
            }
        }
        observeEvents()
    }

    override fun onBackPressed() {
        if (!navigator.navigateBack()) {
            super.onBackPressed()
        }
    }

    private fun observeEvents() {
        navigatorHost.state
            .map { it.canGoBack }
            .observe { binding.mainToolbar.toolbarBackButton.viewVisibility = it }

        navigatorHost.state
            .switchMap { it.activeFragment.viewModel.title }
            .observe { binding.mainToolbar.toolbarTitle.text = it }

        navigatorHost.state
            .switchMap { it.activeFragment.viewModel.progressBarDisplayed }
            .observe { binding.swipeLayout.isRefreshing = it }

        navigatorHost.state
            .observe {
                swipe_layout.isEnabled = it.activeFragment.viewModel is RefreshableViewModel
            }
    }

    private fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(this@MainActivity, Observer { observer(it) })
    }
}

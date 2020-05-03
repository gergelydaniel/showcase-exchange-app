package com.danielgergely.showcase.common

import com.danielgergely.showcase.ExchangeApp
import com.danielgergely.showcase.common.di.AppModule
import com.danielgergely.showcase.common.di.NavigationModule
import com.danielgergely.showcase.common.di.SchedulerModule
import com.danielgergely.showcase.dashboard.di.DashboardFragmentsModule
import com.danielgergely.showcase.dashboard.di.DashboardViewModelModule
import com.danielgergely.showcase.exchange.di.*
import com.danielgergely.showcase.main.di.ActivitiesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        ExchangeModule::class,
        ViewModelModule::class,
        DashboardViewModelModule::class,
        DashboardFragmentsModule::class,
        ExchangeViewModelModule::class,
        ExchangeFragmentsModule::class,
        ActivitiesModule::class,
        AppModule::class,
        ConfigModule::class,
        SchedulerModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ExchangeApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: ExchangeApp)
}

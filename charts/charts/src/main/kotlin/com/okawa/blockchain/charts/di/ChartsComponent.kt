package com.okawa.blockchain.charts.di

import android.content.Context
import com.okawa.blockchain.charts.data.di.ChartsApiModule
import com.okawa.blockchain.charts.data.di.ChartsRepositoryModule
import com.okawa.blockchain.charts.domain.di.ChartsUseCaseModule
import com.okawa.blockchain.charts.ui.ChartsFragment
import com.okawa.blockchain.mkt.di.DaggerDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DaggerDependencies::class],
    modules = [
        ChartsApiModule::class,
        ChartsRepositoryModule::class,
        ChartsUseCaseModule::class,
        ChartsFeatureModule::class
    ]
)
interface ChartsComponent {

    fun inject(chartsFragment: ChartsFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(daggerDependencies: DaggerDependencies): Builder
        fun build(): ChartsComponent
    }
}
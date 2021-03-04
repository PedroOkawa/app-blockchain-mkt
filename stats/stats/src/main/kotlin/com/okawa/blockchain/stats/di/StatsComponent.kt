package com.okawa.blockchain.stats.di

import android.content.Context
import com.okawa.blockchain.mkt.di.DaggerDependencies
import com.okawa.blockchain.stats.data.di.StatsApiModule
import com.okawa.blockchain.stats.data.di.StatsRepositoryModule
import com.okawa.blockchain.stats.domain.di.StatsUseCaseModule
import com.okawa.blockchain.stats.ui.StatsFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DaggerDependencies::class],
    modules = [
        StatsApiModule::class,
        StatsRepositoryModule::class,
        StatsUseCaseModule::class,
        StatsFeatureModule::class
    ]
)
interface StatsComponent {

    fun inject(statsFragment: StatsFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(daggerDependencies: DaggerDependencies): Builder
        fun build(): StatsComponent
    }
}
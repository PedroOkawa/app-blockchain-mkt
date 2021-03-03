package com.okawa.blockchain.pools.di

import android.content.Context
import com.okawa.blockchain.mkt.di.DaggerDependencies
import com.okawa.blockchain.pools.PoolsFragment
import com.okawa.blockchain.pools.data.di.PoolsApiModule
import com.okawa.blockchain.pools.data.di.PoolsRepositoryModule
import com.okawa.blockchain.pools.domain.di.PoolsUseCaseModule
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DaggerDependencies::class],
    modules = [
        PoolsApiModule::class,
        PoolsRepositoryModule::class,
        PoolsUseCaseModule::class,
        PoolsFeatureModule::class
    ]
)
interface PoolsComponent {

    fun inject(poolsFragment: PoolsFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(daggerDependencies: DaggerDependencies): Builder
        fun build(): PoolsComponent
    }
}
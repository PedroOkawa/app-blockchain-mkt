package com.okawa.blockchain.pools.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okawa.blockchain.mkt.di.ViewModelFactory
import com.okawa.blockchain.mkt.di.ViewModelKey
import com.okawa.blockchain.pools.ui.PoolsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PoolsFeatureModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PoolsViewModel::class)
    internal abstract fun poolsViewModel(viewModel: PoolsViewModel): ViewModel
}
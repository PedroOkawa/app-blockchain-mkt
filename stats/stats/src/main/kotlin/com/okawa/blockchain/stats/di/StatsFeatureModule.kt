package com.okawa.blockchain.stats.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okawa.blockchain.mkt.di.ViewModelFactory
import com.okawa.blockchain.mkt.di.ViewModelKey
import com.okawa.blockchain.stats.ui.StatsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StatsFeatureModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(StatsViewModel::class)
    internal abstract fun statsViewModel(viewModel: StatsViewModel): ViewModel
}
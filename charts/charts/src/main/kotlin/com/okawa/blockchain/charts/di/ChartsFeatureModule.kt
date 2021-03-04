package com.okawa.blockchain.charts.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okawa.blockchain.charts.ui.ChartsViewModel
import com.okawa.blockchain.mkt.di.ViewModelFactory
import com.okawa.blockchain.mkt.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChartsFeatureModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ChartsViewModel::class)
    internal abstract fun chartsViewModel(viewModel: ChartsViewModel): ViewModel
}
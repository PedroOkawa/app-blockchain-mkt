package com.okawa.blockchain.charts.data.di

import com.okawa.blockchain.charts.data.repository.ChartsRepositoryImpl
import com.okawa.blockchain.charts.domain.ChartsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ChartsRepositoryModule {

    @Binds
    abstract fun bindsChartsRepository(chartsRepositoryImpl: ChartsRepositoryImpl): ChartsRepository
}
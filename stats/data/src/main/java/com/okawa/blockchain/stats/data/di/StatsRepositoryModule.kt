package com.okawa.blockchain.stats.data.di

import com.okawa.blockchain.stats.data.repository.StatsRepositoryImpl
import com.okawa.blockchain.stats.domain.repository.StatsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class StatsRepositoryModule {

    @Binds
    abstract fun bindsStatsRepository(statsRepositoryImpl: StatsRepositoryImpl): StatsRepository
}
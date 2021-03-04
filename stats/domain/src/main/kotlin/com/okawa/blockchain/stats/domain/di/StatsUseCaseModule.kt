package com.okawa.blockchain.stats.domain.di

import com.okawa.blockchain.stats.domain.repository.StatsRepository
import com.okawa.blockchain.stats.domain.usecase.GetStatsUseCase
import dagger.Module
import dagger.Provides

@Module
class StatsUseCaseModule {

    @Provides
    fun bindsGetStatsUseCase(statsRepository: StatsRepository): GetStatsUseCase {
        return GetStatsUseCase(statsRepository)
    }

}
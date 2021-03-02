package com.okawa.blockchain.charts.domain

import dagger.Module
import dagger.Provides

@Module
class ChartsUseCaseModule {

    @Provides
    fun bindsGetChartsUseCase(chartsRepository: ChartsRepository): GetChartsUseCase {
        return GetChartsUseCase(chartsRepository)
    }

}
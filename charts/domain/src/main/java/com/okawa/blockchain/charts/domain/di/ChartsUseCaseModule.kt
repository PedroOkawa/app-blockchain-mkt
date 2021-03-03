package com.okawa.blockchain.charts.domain.di

import com.okawa.blockchain.charts.domain.repository.ChartsRepository
import com.okawa.blockchain.charts.domain.usecase.GetChartsUseCase
import dagger.Module
import dagger.Provides

@Module
class ChartsUseCaseModule {

    @Provides
    fun bindsGetChartsUseCase(chartsRepository: ChartsRepository): GetChartsUseCase {
        return GetChartsUseCase(chartsRepository)
    }

}
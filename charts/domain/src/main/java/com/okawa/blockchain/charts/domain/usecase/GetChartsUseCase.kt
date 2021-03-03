package com.okawa.blockchain.charts.domain.usecase

import com.okawa.blockchain.charts.domain.model.ChartsDomain
import com.okawa.blockchain.charts.domain.model.ChartsPeriodDomain
import com.okawa.blockchain.charts.domain.model.ChartsRollingAverageDomain
import com.okawa.blockchain.charts.domain.repository.ChartsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChartsUseCase @Inject constructor(
    private val chartsRepository: ChartsRepository
) {

    fun execute(
        chartsPeriodDomain: ChartsPeriodDomain,
        chartsRollingAverageDomain: ChartsRollingAverageDomain = ChartsRollingAverageDomain.TWENTY_FOUR_HOURS
    ): Flow<ChartsDomain> {
        return chartsRepository.getCharts(chartsPeriodDomain.value, chartsRollingAverageDomain.value)
    }
}
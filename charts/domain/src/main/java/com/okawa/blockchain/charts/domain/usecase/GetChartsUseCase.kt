package com.okawa.blockchain.charts.domain.usecase

import com.okawa.blockchain.charts.domain.model.ChartsDomain
import com.okawa.blockchain.charts.domain.model.ChartsPeriodDomain
import com.okawa.blockchain.charts.domain.repository.ChartsRepository
import javax.inject.Inject

class GetChartsUseCase @Inject constructor(
    private val chartsRepository: ChartsRepository
) {

    suspend fun execute(chartsPeriodDomain: ChartsPeriodDomain): ChartsDomain {
        return chartsRepository.getCharts(chartsPeriodDomain.value)
    }
}
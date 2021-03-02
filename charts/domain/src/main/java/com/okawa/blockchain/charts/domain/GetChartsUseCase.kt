package com.okawa.blockchain.charts.domain

import javax.inject.Inject

class GetChartsUseCase @Inject constructor(
    private val chartsRepository: ChartsRepository
) {

    suspend fun execute(timespan: String): ChartsDomain {
        return chartsRepository.getCharts(timespan)
    }
}
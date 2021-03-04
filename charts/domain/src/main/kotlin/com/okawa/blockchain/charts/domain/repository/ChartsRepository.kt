package com.okawa.blockchain.charts.domain.repository

import com.okawa.blockchain.charts.domain.model.ChartsDomain
import kotlinx.coroutines.flow.Flow

interface ChartsRepository {

    fun getCharts(timespan: String, rollingAverage: String): Flow<ChartsDomain>
}
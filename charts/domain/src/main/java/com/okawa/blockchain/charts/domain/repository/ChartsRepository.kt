package com.okawa.blockchain.charts.domain.repository

import com.okawa.blockchain.charts.domain.model.ChartsDomain

interface ChartsRepository {

    suspend fun getCharts(timespan: String): ChartsDomain
}
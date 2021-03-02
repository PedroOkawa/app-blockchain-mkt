package com.okawa.blockchain.charts.domain

interface ChartsRepository {

    suspend fun getCharts(timespan: String): ChartsDomain
}
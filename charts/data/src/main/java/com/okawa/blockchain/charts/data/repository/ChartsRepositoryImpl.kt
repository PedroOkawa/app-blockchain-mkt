package com.okawa.blockchain.charts.data.repository

import com.okawa.blockchain.charts.data.api.BlockchainChartsApi
import com.okawa.blockchain.charts.domain.model.ChartsDomain
import com.okawa.blockchain.charts.domain.repository.ChartsRepository
import javax.inject.Inject

class ChartsRepositoryImpl @Inject constructor(
    private val blockchainChartsApi: BlockchainChartsApi
): ChartsRepository {

    override suspend fun getCharts(timespan: String): ChartsDomain {
        return blockchainChartsApi.getCharts(timespan).toDomain()
    }
}
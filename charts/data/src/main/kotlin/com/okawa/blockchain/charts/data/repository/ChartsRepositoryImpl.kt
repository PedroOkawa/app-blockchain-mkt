package com.okawa.blockchain.charts.data.repository

import com.okawa.blockchain.charts.data.api.BlockchainChartsApi
import com.okawa.blockchain.charts.domain.model.ChartsDomain
import com.okawa.blockchain.charts.domain.repository.ChartsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChartsRepositoryImpl @Inject constructor(
    private val blockchainChartsApi: BlockchainChartsApi
): ChartsRepository {

    override fun getCharts(timespan: String, rollingAverage: String): Flow<ChartsDomain> {
        return flow {
            val result = blockchainChartsApi.getCharts(timespan, rollingAverage).toDomain()
            emit(result)
        }
    }
}
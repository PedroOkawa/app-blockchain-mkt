package com.okawa.blockchain.stats.data.repository

import com.okawa.blockchain.stats.data.api.BlockchainStatsApi
import com.okawa.blockchain.stats.domain.model.StatsDomain
import com.okawa.blockchain.stats.domain.repository.StatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StatsRepositoryImpl @Inject constructor(
    private val blockchainStatsApi: BlockchainStatsApi
): StatsRepository {

    override fun getStats(): Flow<StatsDomain> {
        return flow {
            val result = blockchainStatsApi.getStats().toDomain()
            emit(result)
        }
    }
}
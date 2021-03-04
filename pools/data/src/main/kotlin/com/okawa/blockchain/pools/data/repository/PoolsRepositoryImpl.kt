package com.okawa.blockchain.pools.data.repository

import com.okawa.blockchain.pools.data.api.BlockchainPoolsApi
import com.okawa.blockchain.pools.domain.model.MiningPoolDomain
import com.okawa.blockchain.pools.domain.model.PoolsDomain
import com.okawa.blockchain.pools.domain.repository.PoolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PoolsRepositoryImpl @Inject constructor(
    private val blockchainPoolsApi: BlockchainPoolsApi
): PoolsRepository {

    override fun getPools(timespan: String): Flow<PoolsDomain> {
        return flow {
            val result = blockchainPoolsApi.getPools(timespan)
            emit(PoolsDomain(result.map { MiningPoolDomain(it.key, it.value) }))
        }
    }
}
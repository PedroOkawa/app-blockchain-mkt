package com.okawa.blockchain.pools.domain.usecase

import com.okawa.blockchain.pools.domain.model.PoolsDomain
import com.okawa.blockchain.pools.domain.model.PoolsPeriodDomain
import com.okawa.blockchain.pools.domain.repository.PoolsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPoolsUseCase @Inject constructor(
    private val poolsRepository: PoolsRepository
) {

    fun execute(poolsPeriodDomain: PoolsPeriodDomain): Flow<PoolsDomain> {
        return poolsRepository.getPools(poolsPeriodDomain.value)
    }
}
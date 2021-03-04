package com.okawa.blockchain.pools.domain.repository

import com.okawa.blockchain.pools.domain.model.PoolsDomain
import kotlinx.coroutines.flow.Flow

interface PoolsRepository {

    fun getPools(timespan: String): Flow<PoolsDomain>
}
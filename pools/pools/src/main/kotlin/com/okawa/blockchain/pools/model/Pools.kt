package com.okawa.blockchain.pools.model

import com.okawa.blockchain.pools.domain.model.MiningPoolDomain
import com.okawa.blockchain.pools.domain.model.PoolsDomain

data class Pools(
    val miningPools: List<MiningPool>
)

fun PoolsDomain.toUi(): Pools {
    return Pools(miningPools.map { it.toUi() })
}

data class MiningPool(
    val name: String,
    val distribution: Int
)

fun MiningPoolDomain.toUi(): MiningPool {
    return MiningPool(name, distribution)
}
package com.okawa.blockchain.stats.domain.repository

import com.okawa.blockchain.stats.domain.model.StatsDomain
import kotlinx.coroutines.flow.Flow

interface StatsRepository {

    fun getStats(): Flow<StatsDomain>
}
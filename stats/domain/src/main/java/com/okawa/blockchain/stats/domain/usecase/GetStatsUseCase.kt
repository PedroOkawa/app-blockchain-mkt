package com.okawa.blockchain.stats.domain.usecase

import com.okawa.blockchain.stats.domain.model.StatsDomain
import com.okawa.blockchain.stats.domain.repository.StatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatsUseCase @Inject constructor(
    private val statsRepository: StatsRepository
) {

    fun execute(): Flow<StatsDomain> {
        return statsRepository.getStats()
    }
}
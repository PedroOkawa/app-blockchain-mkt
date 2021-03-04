package com.okawa.blockchain.stats.domain.usecase

import com.google.common.truth.Truth
import com.okawa.blockchain.stats.domain.model.StatsDomain
import com.okawa.blockchain.stats.domain.repository.StatsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@ExperimentalCoroutinesApi
class GetStatsUseCaseTest {

    private val statsRepository: StatsRepository = mockk()
    private val getStatsUseCase: GetStatsUseCase = GetStatsUseCase(statsRepository)

    @Test
    fun `GIVEN successful result WHEN requests stats usecase THEN return stats`() {
        val statsDomain = StatsDomain(
            marketPriceUsd = 1.0,
            hashRate = 1.0,
            totalFeesBtc = 100L,
            period = 200L,
            nTx = 300L,
            nBlocksMined = 4,
            minutesBetweenBlocks = 5.43f,
            totalBc = 600L,
            estimatedTransactionVolumeUsd = 1.0,
            blocksSize = 700L,
            minersRevenueUsd = 1.0,
            nextRetarget = 8,
            difficulty = 900L,
            estimatedBtcSent = 1000L,
            minersRevenueBtc = 1100L,
            totalBtcSent = 1200L,
            tradeVolumeBtc = 1.0,
            tradeVolumeUsd = 1.0,
            timestamp = 1300L
        )

        every { statsRepository.getStats() } returns flowOf(statsDomain)

        runBlockingTest {
            getStatsUseCase.execute().collect { result ->
                Truth.assertThat(result).isEqualTo(statsDomain)
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN requests stats usecase THEN return flow with exception`() {
        every { statsRepository.getStats() } returns flow { throw Exception() }

        assertThrows<Exception> {
            runBlockingTest {
                getStatsUseCase.execute().collect()
            }
        }
    }
}
package com.okawa.blockchain.stats.data

import com.google.common.truth.Truth
import com.okawa.blockchain.stats.data.api.BlockchainStatsApi
import com.okawa.blockchain.stats.data.model.StatsEntity
import com.okawa.blockchain.stats.data.repository.StatsRepositoryImpl
import com.okawa.blockchain.stats.domain.repository.StatsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.jupiter.api.assertThrows

@ExperimentalCoroutinesApi
class PoolsRepositoryTest {

    private val blockchainStatsApi: BlockchainStatsApi = mockk()
    private val statsRepository: StatsRepository =  StatsRepositoryImpl(blockchainStatsApi)

    @Test
    fun `GIVEN successful result WHEN user requests stats THEN return flow with stats domain model`() {
        val statsEntity = StatsEntity(
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

        val expectedResult = statsEntity.toDomain()
        coEvery { blockchainStatsApi.getStats() } returns statsEntity

        runBlockingTest {
            statsRepository.getStats().collect { result ->
                Truth.assertThat(result).isEqualTo(expectedResult)
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN user requests stats THEN return flow with exception`() {
        coEvery { blockchainStatsApi.getStats() } throws Exception()

        assertThrows<Exception> {
            runBlockingTest {
                statsRepository.getStats().collect()
            }
        }
    }
}
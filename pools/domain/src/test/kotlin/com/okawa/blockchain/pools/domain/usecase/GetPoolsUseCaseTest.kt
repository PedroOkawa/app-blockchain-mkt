package com.okawa.blockchain.pools.domain.usecase

import com.google.common.truth.Truth
import com.okawa.blockchain.pools.domain.model.MiningPoolDomain
import com.okawa.blockchain.pools.domain.model.PoolsDomain
import com.okawa.blockchain.pools.domain.model.PoolsPeriodDomain
import com.okawa.blockchain.pools.domain.repository.PoolsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private const val ARGUMENT_TIME_SPAN = "4days"

@ExperimentalCoroutinesApi
class GetPoolsUseCaseTest {

    private val poolsRepository: PoolsRepository = mockk()
    private val getPoolsUseCase: GetPoolsUseCase = GetPoolsUseCase(poolsRepository)

    @Test
    fun `GIVEN successful result WHEN requests pools usecase THEN return pools`() {
        val poolsDomain = PoolsDomain(
            miningPools = listOf(
                MiningPoolDomain("GHash.IO", 7),
                MiningPoolDomain("95.128.48.209", 1),
                MiningPoolDomain("NiceHash Solo", 1),
                MiningPoolDomain("Solo CKPool", 2),
                MiningPoolDomain("176.9.31.178", 1),
                MiningPoolDomain("1Hash", 11),
                MiningPoolDomain("217.11.225.189", 1),
                MiningPoolDomain("Unknown", 10),
                MiningPoolDomain("BitClub Network", 23)
            )
        )

        every { poolsRepository.getPools(ARGUMENT_TIME_SPAN) } returns flowOf(poolsDomain)

        runBlockingTest {
            getPoolsUseCase.execute(PoolsPeriodDomain.FOUR_DAYS).collect { result ->
                Truth.assertThat(result).isEqualTo(poolsDomain)
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN requests pools usecase THEN return flow with exception`() {
        every { poolsRepository.getPools(ARGUMENT_TIME_SPAN) } returns flow { throw Exception() }

        assertThrows<Exception> {
            runBlockingTest {
                getPoolsUseCase.execute(PoolsPeriodDomain.FOUR_DAYS).collect()
            }
        }
    }
}
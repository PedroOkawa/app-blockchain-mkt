package com.okawa.blockchain.pools.data.repository

import com.google.common.truth.Truth
import com.okawa.blockchain.pools.data.api.BlockchainPoolsApi
import com.okawa.blockchain.pools.domain.model.MiningPoolDomain
import com.okawa.blockchain.pools.domain.model.PoolsDomain
import com.okawa.blockchain.pools.domain.repository.PoolsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.jupiter.api.assertThrows

private const val ARGUMENT_TIME_SPAN = "4days"

@ExperimentalCoroutinesApi
class PoolsRepositoryTest {

    private val blockchainPoolsApi: BlockchainPoolsApi = mockk()
    private val poolsRepository: PoolsRepository = PoolsRepositoryImpl(blockchainPoolsApi)

    @Test
    fun `GIVEN successful result WHEN requests pools on repository THEN return flow with pools domain model`() {
        val poolsEntity = mapOf(
            "GHash.IO" to  7,
            "95.128.48.209" to  1,
            "NiceHash Solo" to  1,
            "Solo CKPool" to  2,
            "176.9.31.178" to  1,
            "1Hash" to  11,
            "217.11.225.189" to  1,
            "Unknown" to  10,
            "BitClub Network" to  23
        )

        val expectedResult = PoolsDomain(poolsEntity.map { MiningPoolDomain(it.key, it.value) })
        coEvery { blockchainPoolsApi.getPools(ARGUMENT_TIME_SPAN) } returns poolsEntity

        runBlockingTest {
            poolsRepository.getPools(ARGUMENT_TIME_SPAN).collect { result ->
                Truth.assertThat(result).isEqualTo(expectedResult)
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN user requests pools THEN return flow with exception`() {
        coEvery { blockchainPoolsApi.getPools(ARGUMENT_TIME_SPAN) } throws Exception()

        assertThrows<Exception> {
            runBlockingTest {
                poolsRepository.getPools(ARGUMENT_TIME_SPAN).collect()
            }
        }
    }
}
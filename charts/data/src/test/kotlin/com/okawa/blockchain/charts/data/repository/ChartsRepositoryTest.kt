package com.okawa.blockchain.charts.data.repository

import com.google.common.truth.Truth
import com.okawa.blockchain.charts.data.api.BlockchainChartsApi
import com.okawa.blockchain.charts.data.model.ChartsEntity
import com.okawa.blockchain.charts.data.model.ChartsValueEntity
import com.okawa.blockchain.charts.domain.repository.ChartsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.jupiter.api.assertThrows

private const val ARGUMENT_TIME_SPAN = "7days"
private const val ARGUMENT_ROLLING_AVERAGE = "8hours"

@ExperimentalCoroutinesApi
class ChartsRepositoryTest {

    private val blockchainChartsApi: BlockchainChartsApi = mockk()
    private val chartsRepository: ChartsRepository = ChartsRepositoryImpl(blockchainChartsApi)

    @Test
    fun `GIVEN successful result WHEN requests charts on repository THEN return flow with charts domain model`() {
        val chartsEntity = ChartsEntity(
            status = "OK",
            name = "Chart name",
            unit = "Transactions",
            period = "day",
            description = "The number of daily confirmed Bitcoin transactions.",
            values = listOf(
                ChartsValueEntity(
                    timestamp = 1442534400,
                    value = 188330.0f
                )
            )
        )

        val expectedResult = chartsEntity.toDomain()
        coEvery { blockchainChartsApi.getCharts(ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE) } returns chartsEntity

        runBlockingTest {
            chartsRepository.getCharts(ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE).collect { result ->
                Truth.assertThat(result).isEqualTo(expectedResult)
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN user requests charts THEN return flow with exception`() {
        coEvery { blockchainChartsApi.getCharts(ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE) } throws Exception()

        assertThrows<Exception> {
            runBlockingTest {
                chartsRepository.getCharts(ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE).collect()
            }
        }
    }
}
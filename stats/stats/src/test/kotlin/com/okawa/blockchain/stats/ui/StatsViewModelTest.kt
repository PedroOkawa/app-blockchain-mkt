package com.okawa.blockchain.stats.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.okawa.blockchain.stats.domain.model.StatsDomain
import com.okawa.blockchain.stats.domain.usecase.GetStatsUseCase
import com.okawa.blockchain.stats.toUi
import com.okawa.blockchain.testutils.TestCoroutineRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class StatsViewModelTest {

    private val getStatsUseCase: GetStatsUseCase = mockk()
    private val statsViewModel: StatsViewModel = StatsViewModel(getStatsUseCase)
    private val observer: Observer<StatsViewModel.ViewState> = spyk(Observer {  })

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `GIVEN successful result WHEN user opens stats feature THEN show loading and successful response sequentially`() {
        testCoroutineRule.runBlockingTest {
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
            val expectedResult = statsDomain.toUi()

            every { getStatsUseCase.execute() } returns flowOf(statsDomain)

            statsViewModel.viewState.observeForever(observer)

            statsViewModel.retrieveStats()

            verifySequence {
                observer.onChanged(StatsViewModel.ViewState.Loading)
                observer.onChanged(StatsViewModel.ViewState.Success(expectedResult))
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN user opens stats feature THEN show loading and error response sequentially`() {
        testCoroutineRule.runBlockingTest {
            every { getStatsUseCase.execute() } returns flow { throw Exception() }

            statsViewModel.viewState.observeForever(observer)

            statsViewModel.retrieveStats()

            verifySequence {
                observer.onChanged(StatsViewModel.ViewState.Loading)
                observer.onChanged(StatsViewModel.ViewState.Error)
            }
        }
    }
}
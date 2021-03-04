package com.okawa.blockchain.charts.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.okawa.blockchain.charts.domain.model.ChartsDomain
import com.okawa.blockchain.charts.domain.model.ChartsPeriodDomain
import com.okawa.blockchain.charts.domain.model.ChartsValueDomain
import com.okawa.blockchain.charts.domain.usecase.GetChartsUseCase
import com.okawa.blockchain.charts.model.ChartsPeriod
import com.okawa.blockchain.charts.model.toUi
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
class ChartsViewModelTest {

    private val getChartsUseCase: GetChartsUseCase = mockk()
    private val chartsViewModel: ChartsViewModel = ChartsViewModel(getChartsUseCase)
    private val observer: Observer<ChartsViewModel.ViewState> = spyk(Observer {  })

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `GIVEN successful result WHEN user opens charts feature THEN show loading and successful response sequentially`() {
        testCoroutineRule.runBlockingTest {
            val chartsDomain = ChartsDomain(
                name = "Chart name",
                unit = "Transactions",
                period = "day",
                description = "The number of daily confirmed Bitcoin transactions.",
                values = listOf(
                    ChartsValueDomain(
                        timestamp = 1442534400,
                        value = 188330.0f
                    )
                )
            )
            val expectedResult = chartsDomain.toUi()

            every { getChartsUseCase.execute(ChartsPeriodDomain.ONE_WEEK) } returns flowOf(chartsDomain)

            chartsViewModel.viewState.observeForever(observer)

            chartsViewModel.retrieveCharts(ChartsPeriod.ONE_WEEK)

            verifySequence {
                observer.onChanged(ChartsViewModel.ViewState.Loading)
                observer.onChanged(ChartsViewModel.ViewState.Success(expectedResult))
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN user opens charts feature THEN show loading and error response sequentially`() {
        testCoroutineRule.runBlockingTest {
            every { getChartsUseCase.execute(ChartsPeriodDomain.ONE_WEEK) } returns flow { throw Exception() }

            chartsViewModel.viewState.observeForever(observer)

            chartsViewModel.retrieveCharts(ChartsPeriod.ONE_WEEK)

            verifySequence {
                observer.onChanged(ChartsViewModel.ViewState.Loading)
                observer.onChanged(ChartsViewModel.ViewState.Error)
            }
        }
    }
}
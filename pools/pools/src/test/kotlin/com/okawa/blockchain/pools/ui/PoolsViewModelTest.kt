package com.okawa.blockchain.pools.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.okawa.blockchain.pools.domain.model.MiningPoolDomain
import com.okawa.blockchain.pools.domain.model.PoolsDomain
import com.okawa.blockchain.pools.domain.model.PoolsPeriodDomain
import com.okawa.blockchain.pools.domain.usecase.GetPoolsUseCase
import com.okawa.blockchain.pools.model.PoolsPeriod
import com.okawa.blockchain.pools.model.toUi
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
class PoolsViewModelTest {

    private val getPoolsUseCase: GetPoolsUseCase = mockk()
    private val poolsViewModel: PoolsViewModel = PoolsViewModel(getPoolsUseCase)
    private val observer: Observer<PoolsViewModel.ViewState> = spyk(Observer {  })

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `GIVEN successful result WHEN user opens pools feature THEN show loading and successful response sequentially`() {
        testCoroutineRule.runBlockingTest {
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
            val expectedResult = poolsDomain.toUi()

            every { getPoolsUseCase.execute(PoolsPeriodDomain.FOUR_DAYS) } returns flowOf(poolsDomain)

            poolsViewModel.viewState.observeForever(observer)

            poolsViewModel.retrievePools(PoolsPeriod.FOUR_DAYS)

            verifySequence {
                observer.onChanged(PoolsViewModel.ViewState.Loading)
                observer.onChanged(PoolsViewModel.ViewState.Success(expectedResult))
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN user opens pools feature THEN show loading and error response sequentially`() {
        testCoroutineRule.runBlockingTest {
            every { getPoolsUseCase.execute(PoolsPeriodDomain.FOUR_DAYS) } returns flow { throw Exception() }

            poolsViewModel.viewState.observeForever(observer)

            poolsViewModel.retrievePools(PoolsPeriod.FOUR_DAYS)

            verifySequence {
                observer.onChanged(PoolsViewModel.ViewState.Loading)
                observer.onChanged(PoolsViewModel.ViewState.Error)
            }
        }
    }
}
package com.okawa.blockchain.mkt.ui.tutorial

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.okawa.blockchain.mkt.ui.tutorial.model.TutorialPage
import com.okawa.blockchain.mkt.utils.ColorProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

private const val STATS_COLOR = 1
private const val CHARTS_COLOR = 2
private const val POOLS_COLOR = 3
private const val FINAL_COLOR = 4

class TutorialViewModelTest {

    private val colorProvider: ColorProvider = mockk()
    private val tutorialViewModel: TutorialViewModel = TutorialViewModel(colorProvider)
    private val observer: Observer<TutorialViewModel.ViewState> = spyk(Observer {  })

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `GIVEN tutorial stats page WHEN the user scrolls to the stats page THEN show the appropriate background color`() {
        val expectedResult = TutorialViewModel.ViewState(STATS_COLOR, false)
        every { colorProvider.statsPageColor } returns STATS_COLOR

        tutorialViewModel.viewState.observeForever(observer)

        tutorialViewModel.onPageSelected(TutorialPage.PAGE_STATS)

        verify(exactly = 1) { observer.onChanged(expectedResult) }
    }

    @Test
    fun `GIVEN tutorial charts page WHEN the user scrolls to the charts page THEN show the appropriate background color`() {
        val expectedResult = TutorialViewModel.ViewState(CHARTS_COLOR, false)
        every { colorProvider.chartsPageColor } returns CHARTS_COLOR

        tutorialViewModel.viewState.observeForever(observer)

        tutorialViewModel.onPageSelected(TutorialPage.PAGE_CHARTS)

        verify(exactly = 1) { observer.onChanged(expectedResult) }
    }

    @Test
    fun `GIVEN tutorial pools page WHEN the user scrolls to the pools page THEN show the appropriate background color`() {
        val expectedResult = TutorialViewModel.ViewState(POOLS_COLOR, false)
        every { colorProvider.poolsPageColor } returns POOLS_COLOR

        tutorialViewModel.viewState.observeForever(observer)

        tutorialViewModel.onPageSelected(TutorialPage.PAGE_POOLS)

        verify(exactly = 1) { observer.onChanged(expectedResult) }
    }

    @Test
    fun `GIVEN tutorial last page WHEN the user scrolls to the last page THEN show the appropriate background color`() {
        val expectedResult = TutorialViewModel.ViewState(FINAL_COLOR, true)
        every { colorProvider.finalPageColor } returns FINAL_COLOR

        tutorialViewModel.viewState.observeForever(observer)

        tutorialViewModel.onPageSelected(TutorialPage.PAGE_FINAL)

        verify(exactly = 1) { observer.onChanged(expectedResult) }
    }
}
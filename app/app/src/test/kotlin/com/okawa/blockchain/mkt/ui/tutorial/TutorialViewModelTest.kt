package com.okawa.blockchain.mkt.ui.tutorial

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.okawa.blockchain.mkt.R
import com.okawa.blockchain.mkt.ui.tutorial.model.TutorialPage
import io.mockk.spyk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TutorialViewModelTest {

    private val tutorialViewModel: TutorialViewModel = TutorialViewModel()
    private val observer: Observer<TutorialViewModel.ViewState> = spyk(Observer {  })

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `GIVEN tutorial stats page WHEN the user scrolls to the stats page THEN show the appropriate background color`() {
        val expectedResult = TutorialViewModel.ViewState(R.color.tutorial_page_stats_color, false)
        tutorialViewModel.viewState.observeForever(observer)

        tutorialViewModel.onPageSelected(TutorialPage.PAGE_STATS)

        verify(exactly = 1) { observer.onChanged(expectedResult) }
    }

    @Test
    fun `GIVEN tutorial charts page WHEN the user scrolls to the charts page THEN show the appropriate background color`() {
        val expectedResult = TutorialViewModel.ViewState(R.color.tutorial_page_charts_color, false)

        tutorialViewModel.viewState.observeForever(observer)

        tutorialViewModel.onPageSelected(TutorialPage.PAGE_CHARTS)

        verify(exactly = 1) { observer.onChanged(expectedResult) }
    }

    @Test
    fun `GIVEN tutorial pools page WHEN the user scrolls to the pools page THEN show the appropriate background color`() {
        val expectedResult = TutorialViewModel.ViewState(R.color.tutorial_page_pools_color, false)

        tutorialViewModel.viewState.observeForever(observer)

        tutorialViewModel.onPageSelected(TutorialPage.PAGE_POOLS)

        verify(exactly = 1) { observer.onChanged(expectedResult) }
    }

    @Test
    fun `GIVEN tutorial last page WHEN the user scrolls to the last page THEN show the appropriate background color`() {
        val expectedResult = TutorialViewModel.ViewState(R.color.tutorial_page_final_color, true)

        tutorialViewModel.viewState.observeForever(observer)

        tutorialViewModel.onPageSelected(TutorialPage.PAGE_FINAL)

        verify(exactly = 1) { observer.onChanged(expectedResult) }
    }
}
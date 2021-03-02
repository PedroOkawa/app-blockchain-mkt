package com.okawa.blockchain.mkt.ui.tutorial

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.okawa.blockchain.mkt.R
import com.okawa.blockchain.mkt.ui.tutorial.model.TutorialPage

private const val TOTAL_PAGES = 4

class TutorialPagerAdapter(
    fragment: Fragment

): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = TOTAL_PAGES

    override fun createFragment(position: Int): Fragment {
        val (imageRes, descriptionRes) = when(position) {
            TutorialPage.PAGE_CHARTS -> Pair(R.drawable.ic_chart_24, R.string.tutorial_description_charts)
            TutorialPage.PAGE_STATS -> Pair(R.drawable.ic_stats_24, R.string.tutorial_description_stats)
            TutorialPage.PAGE_POOLS -> Pair(R.drawable.ic_pools_24, R.string.tutorial_description_pools)
            TutorialPage.PAGE_FINAL -> Pair(R.drawable.ic_chart_24, R.string.tutorial_description_final)
            else -> throw IllegalStateException()
        }

        return TutorialPageFragment.newInstance(imageRes, descriptionRes)
    }
}
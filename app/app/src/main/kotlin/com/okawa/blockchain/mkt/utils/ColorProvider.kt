package com.okawa.blockchain.mkt.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.okawa.blockchain.mkt.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ColorProvider @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    val statsPageColor: Int by lazy { getColor(R.color.tutorial_page_stats_color) }
    val chartsPageColor: Int by lazy { getColor(R.color.tutorial_page_charts_color) }
    val poolsPageColor: Int by lazy { getColor(R.color.tutorial_page_pools_color) }
    val finalPageColor: Int by lazy { getColor(R.color.tutorial_page_final_color) }

    private fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }
}
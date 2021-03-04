package com.okawa.blockchain.charts.utils

import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.okawa.blockchain.charts.R
import com.okawa.blockchain.charts.di.DaggerChartsComponent
import com.okawa.blockchain.charts.ui.ChartsFragment
import com.okawa.blockchain.mkt.di.DaggerDependencies
import dagger.hilt.android.EntryPointAccessors

internal fun ChartsFragment.inject() {
    DaggerChartsComponent
        .builder()
        .context(requireContext())
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(),
                DaggerDependencies::class.java
            )
        )
        .build()
        .inject(this)
}

@ColorInt
internal fun ChartsFragment.getGraphDataSetColor(): Int {
    return ContextCompat.getColor(requireContext(), R.color.chart_line_color)
}

@ColorInt
internal fun ChartsFragment.getGraphDataHightlightColor(): Int {
    return ContextCompat.getColor(requireContext(), R.color.chart_line_highlight_color)
}
package com.okawa.blockchain.pools.utils

import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.okawa.blockchain.mkt.di.DaggerDependencies
import com.okawa.blockchain.pools.R
import com.okawa.blockchain.pools.di.DaggerPoolsComponent
import com.okawa.blockchain.pools.ui.PoolsFragment
import dagger.hilt.android.EntryPointAccessors

internal fun PoolsFragment.inject() {
    DaggerPoolsComponent
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
internal fun PoolsFragment.getGraphDataColors(): List<Int> {
    return listOf(
        ContextCompat.getColor(requireContext(), R.color.chart_pie_first_color),
        ContextCompat.getColor(requireContext(), R.color.chart_pie_second_color),
        ContextCompat.getColor(requireContext(), R.color.chart_pie_third_color),
        ContextCompat.getColor(requireContext(), R.color.chart_pie_fourth_color),
        ContextCompat.getColor(requireContext(), R.color.chart_pie_fifth_color)
    )
}

@ColorInt
internal fun PoolsFragment.getGraphLabelColor(): Int {
    return ContextCompat.getColor(requireContext(), R.color.chart_pie_label_color)
}
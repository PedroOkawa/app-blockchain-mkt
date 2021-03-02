package com.okawa.blockchain.charts.ui

import com.okawa.blockchain.charts.di.DaggerChartsComponent
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
package com.okawa.blockchain.stats.utils

import com.okawa.blockchain.mkt.di.DaggerDependencies
import com.okawa.blockchain.stats.di.DaggerStatsComponent
import com.okawa.blockchain.stats.ui.StatsFragment
import dagger.hilt.android.EntryPointAccessors

internal fun StatsFragment.inject() {
    DaggerStatsComponent
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

package com.okawa.blockchain.pools.utils

import com.okawa.blockchain.mkt.di.DaggerDependencies
import com.okawa.blockchain.pools.PoolsFragment
import com.okawa.blockchain.pools.di.DaggerPoolsComponent
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
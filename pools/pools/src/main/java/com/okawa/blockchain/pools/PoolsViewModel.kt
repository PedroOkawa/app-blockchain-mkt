package com.okawa.blockchain.pools

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okawa.blockchain.pools.domain.model.PoolsPeriodDomain
import com.okawa.blockchain.pools.domain.usecase.GetPoolsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PoolsViewModel @Inject constructor(
    private val getPoolsUseCase: GetPoolsUseCase
) : ViewModel() {

    fun test() {
        getPoolsUseCase
            .execute(PoolsPeriodDomain.TWNTY_FOUR_HOURS)
            .onEach {
                Log.w("TEST", "RESULT: $it")
            }
            .launchIn(viewModelScope)
    }
}
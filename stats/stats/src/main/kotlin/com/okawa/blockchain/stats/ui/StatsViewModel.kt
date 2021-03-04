package com.okawa.blockchain.stats.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okawa.blockchain.stats.Stats
import com.okawa.blockchain.stats.domain.usecase.GetStatsUseCase
import com.okawa.blockchain.stats.toUi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class StatsViewModel @Inject constructor(
    private val getStatsUseCase: GetStatsUseCase
) : ViewModel() {

    sealed class ViewState {
        object Error : ViewState()
        object Loading : ViewState()
        data class Success(val stats: Stats): ViewState()
    }

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState


    fun retrieveStats() {
        getStatsUseCase
            .execute()
            .onStart { _viewState.postValue(ViewState.Loading) }
            .onEach { _viewState.postValue(ViewState.Success(it.toUi())) }
            .catch { _viewState.postValue(ViewState.Error) }
            .launchIn(viewModelScope)
    }
}
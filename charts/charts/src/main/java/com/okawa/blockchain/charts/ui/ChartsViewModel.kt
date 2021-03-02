package com.okawa.blockchain.charts.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okawa.blockchain.charts.domain.GetChartsUseCase
import com.okawa.blockchain.charts.model.Charts
import com.okawa.blockchain.charts.model.toUi
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChartsViewModel @Inject constructor(
    private val useCase: GetChartsUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<Charts>()
    val viewState: LiveData<Charts> = _viewState

    fun retrieveCharts() {
        viewModelScope.launch {
            val response = useCase.execute("5days").toUi()
            _viewState.postValue(response)
        }
    }
}
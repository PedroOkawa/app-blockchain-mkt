package com.okawa.blockchain.charts.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okawa.blockchain.charts.domain.usecase.GetChartsUseCase
import com.okawa.blockchain.charts.model.Charts
import com.okawa.blockchain.charts.model.ChartsPeriod
import com.okawa.blockchain.charts.model.toUi
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChartsViewModel @Inject constructor(
    private val useCase: GetChartsUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<Charts>()
    val viewState: LiveData<Charts> = _viewState

    fun retrieveCharts(chartsPeriod: ChartsPeriod = ChartsPeriod.ONE_MONTH) {
        viewModelScope.launch {
            val response = useCase.execute(chartsPeriod.toDomain()).toUi(chartsPeriod)
            _viewState.postValue(response)
        }
    }
}
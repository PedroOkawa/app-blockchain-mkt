package com.okawa.blockchain.mkt.ui.tutorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okawa.blockchain.mkt.ui.tutorial.model.TutorialPage
import com.okawa.blockchain.mkt.utils.ColorProvider
import com.okawa.blockchain.mkt.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val colorProvider: ColorProvider
) : ViewModel() {

    data class ViewState(
        val color: Int,
        val isLastPage: Boolean
    )

    private val _navigation = SingleLiveEvent<Unit>()
    val navigation: LiveData<Unit> = _navigation

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun onPageSelected(position: Int) {
        val color = when (position) {
            TutorialPage.PAGE_STATS -> colorProvider.statsPageColor
            TutorialPage.PAGE_CHARTS -> colorProvider.chartsPageColor
            TutorialPage.PAGE_POOLS -> colorProvider.poolsPageColor
            TutorialPage.PAGE_FINAL -> colorProvider.finalPageColor
            else -> throw IllegalStateException()
        }

        _viewState.postValue(ViewState(color, position == TutorialPage.PAGE_FINAL))
    }

    fun onCloseTapped() {
        _navigation.call()
    }
}
package com.okawa.blockchain.charts.model

import androidx.annotation.StringRes
import com.okawa.blockchain.charts.R
import com.okawa.blockchain.charts.domain.model.ChartsPeriodDomain

enum class ChartsPeriod(@StringRes val value: Int) {
    ONE_WEEK(R.string.period_one_week),
    ONE_MONTH(R.string.period_one_month),
    ONE_YEAR(R.string.period_one_year),
    THREE_YEAR(R.string.period_three_years);

    fun toDomain(): ChartsPeriodDomain {
        return ChartsPeriodDomain.values().firstOrNull { it.name == name } ?: ChartsPeriodDomain.ONE_MONTH
    }
}
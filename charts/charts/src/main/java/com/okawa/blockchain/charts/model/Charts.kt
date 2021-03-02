package com.okawa.blockchain.charts.model

import com.okawa.blockchain.charts.domain.ChartsDomain
import com.okawa.blockchain.charts.domain.ChartsValueDomain
import java.util.*

data class Charts(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<ChartsValue>
)

fun ChartsDomain.toUi(): Charts {
    return Charts(
        name = name,
        unit = unit,
        period = period,
        description = description,
        values = values.map { it.toUi() }
    )
}

data class ChartsValue(
    val time: Date,
    val value: Double
)

fun ChartsValueDomain.toUi(): ChartsValue {
    return ChartsValue(
        time = time,
        value = value
    )
}
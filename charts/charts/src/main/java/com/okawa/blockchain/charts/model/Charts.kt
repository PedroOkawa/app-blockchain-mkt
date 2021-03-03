package com.okawa.blockchain.charts.model

import com.okawa.blockchain.charts.domain.model.ChartsDomain
import com.okawa.blockchain.charts.domain.model.ChartsValueDomain
import java.text.SimpleDateFormat
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
    val timestamp: String,
    val value: Float
)

fun ChartsValueDomain.toUi(): ChartsValue {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return ChartsValue(
        timestamp = formatter.format(Date((timestamp * 1000))),
        value = value
    )
}
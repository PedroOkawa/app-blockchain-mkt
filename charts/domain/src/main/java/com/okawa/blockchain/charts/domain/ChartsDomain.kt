package com.okawa.blockchain.charts.domain

import java.util.*

data class ChartsDomain(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<ChartsValueDomain>
)

data class ChartsValueDomain(
    val time: Date,
    val value: Double
)
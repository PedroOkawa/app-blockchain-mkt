package com.okawa.blockchain.charts.domain.model

data class ChartsDomain(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<ChartsValueDomain>
)

data class ChartsValueDomain(
    val timestamp: Long,
    val value: Float
)
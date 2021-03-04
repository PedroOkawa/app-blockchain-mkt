package com.okawa.blockchain.charts.data.model

import com.okawa.blockchain.charts.domain.model.ChartsDomain
import com.okawa.blockchain.charts.domain.model.ChartsValueDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChartsEntity(
    @Json(name = "status")
    val status: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "unit")
    val unit: String,
    @Json(name = "period")
    val period: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "values")
    val values: List<ChartsValueEntity>
) {
    fun toDomain(): ChartsDomain {
        return ChartsDomain(
            name = name,
            unit = unit,
            period = period,
            description = description,
            values = values.map { it.toDomain() }
        )
    }
}

@JsonClass(generateAdapter = true)
data class ChartsValueEntity(
    @Json(name = "x")
    val timestamp: Long,
    @Json(name = "y")
    val value: Float
) {
    fun toDomain(): ChartsValueDomain {
        return ChartsValueDomain(
            timestamp = timestamp,
            value = value
        )
    }
}

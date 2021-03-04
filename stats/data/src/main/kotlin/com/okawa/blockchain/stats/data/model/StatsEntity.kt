package com.okawa.blockchain.stats.data.model

import com.okawa.blockchain.stats.domain.model.StatsDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatsEntity(
    @Json(name = "market_price_usd")
    val marketPriceUsd: Double,
    @Json(name = "hash_rate")
    val hashRate: Double,
    @Json(name = "total_fees_btc")
    val totalFeesBtc: Long,
    @Json(name = "n_btc_mined")
    val period: Long,
    @Json(name = "n_tx")
    val nTx: Long,
    @Json(name = "n_blocks_mined")
    val nBlocksMined: Int,
    @Json(name = "minutes_between_blocks")
    val minutesBetweenBlocks: Float,
    @Json(name = "totalbc")
    val totalBc: Long,
    @Json(name = "estimated_transaction_volume_usd")
    val estimatedTransactionVolumeUsd: Double,
    @Json(name = "blocks_size")
    val blocksSize: Long,
    @Json(name = "miners_revenue_usd")
    val minersRevenueUsd: Double,
    @Json(name = "nextretarget")
    val nextRetarget: Int,
    @Json(name = "difficulty")
    val difficulty: Long,
    @Json(name = "estimated_btc_sent")
    val estimatedBtcSent: Long,
    @Json(name = "miners_revenue_btc")
    val minersRevenueBtc: Long,
    @Json(name = "total_btc_sent")
    val totalBtcSent: Long,
    @Json(name = "trade_volume_btc")
    val tradeVolumeBtc: Double,
    @Json(name = "trade_volume_usd")
    val tradeVolumeUsd: Double,
    @Json(name = "timestamp")
    val timestamp: Long
) {
    fun toDomain(): StatsDomain {
        return StatsDomain(
            marketPriceUsd,
            hashRate,
            totalFeesBtc,
            period,
            nTx,
            nBlocksMined,
            minutesBetweenBlocks,
            totalBc,
            estimatedTransactionVolumeUsd,
            blocksSize,
            minersRevenueUsd,
            nextRetarget,
            difficulty,
            estimatedBtcSent,
            minersRevenueBtc,
            totalBtcSent,
            tradeVolumeBtc,
            tradeVolumeUsd,
            timestamp
        )
    }
}

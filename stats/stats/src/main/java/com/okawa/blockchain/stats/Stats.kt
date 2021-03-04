package com.okawa.blockchain.stats

import com.okawa.blockchain.stats.domain.model.StatsDomain
import java.text.NumberFormat
import java.util.*

data class Stats(
    val marketPriceUsd: String,
    val hashRate: Double,
    val totalFeesBtc: String,
    val period: Long,
    val nTx: String,
    val nBlocksMined: String,
    val minutesBetweenBlocks: String,
    val totalBc: String,
    val estimatedTransactionVolumeUsd: String,
    val blocksSize: Long,
    val minersRevenueUsd: String,
    val nextRetarget: Int,
    val difficulty: Long,
    val estimatedBtcSent: String,
    val minersRevenueBtc: String,
    val totalBtcSent: String,
    val tradeVolumeBtc: String,
    val tradeVolumeUsd: String,
    val timestamp: Long
)

fun StatsDomain.toUi(): Stats {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance(Locale.US)

    return Stats(
        format.format(marketPriceUsd),
        hashRate,
        totalFeesBtc.toString(),
        period,
        nTx.toString(),
        nBlocksMined.toString(),
        minutesBetweenBlocks.toString(),
        totalBc.toString(),
        format.format(estimatedTransactionVolumeUsd),
        blocksSize,
        format.format(minersRevenueUsd),
        nextRetarget,
        difficulty,
        estimatedBtcSent.toString(),
        minersRevenueBtc.toString(),
        totalBtcSent.toString(),
        tradeVolumeBtc.toString(),
        format.format(tradeVolumeUsd),
        timestamp
    )
}
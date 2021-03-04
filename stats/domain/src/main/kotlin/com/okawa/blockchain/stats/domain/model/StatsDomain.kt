package com.okawa.blockchain.stats.domain.model

data class StatsDomain(
    val marketPriceUsd: Double,
    val hashRate: Double,
    val totalFeesBtc: Long,
    val period: Long,
    val nTx: Long,
    val nBlocksMined: Int,
    val minutesBetweenBlocks: Float,
    val totalBc: Long,
    val estimatedTransactionVolumeUsd: Double,
    val blocksSize: Long,
    val minersRevenueUsd: Double,
    val nextRetarget: Int,
    val difficulty: Long,
    val estimatedBtcSent: Long,
    val minersRevenueBtc: Long,
    val totalBtcSent: Long,
    val tradeVolumeBtc: Double,
    val tradeVolumeUsd: Double,
    val timestamp: Long
)
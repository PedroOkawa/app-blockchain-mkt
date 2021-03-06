package com.okawa.blockchain.charts.data.api

import com.okawa.blockchain.charts.data.model.ChartsEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainChartsApi {

    @GET("charts/transactions-per-second")
    suspend fun getCharts(
        @Query(value = "timespan")
        timespan: String,
        @Query(value = "rollingAverage")
        rollingAverage: String
    ): ChartsEntity
}
package com.okawa.blockchain.stats.data.api

import com.okawa.blockchain.stats.data.model.StatsEntity
import retrofit2.http.GET

interface BlockchainStatsApi {

    @GET("stats")
    suspend fun getStats(): StatsEntity
}
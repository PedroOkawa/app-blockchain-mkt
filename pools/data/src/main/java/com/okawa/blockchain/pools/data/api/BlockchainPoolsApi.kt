package com.okawa.blockchain.pools.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainPoolsApi {

    @GET("pools")
    suspend fun getPools(
        @Query(value = "timespan")
        timespan: String
    ): Map<String, Int>
}
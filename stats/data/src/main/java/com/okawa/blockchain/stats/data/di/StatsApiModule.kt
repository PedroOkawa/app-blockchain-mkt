package com.okawa.blockchain.stats.data.di

import com.okawa.blockchain.stats.data.api.BlockchainStatsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class StatsApiModule {

    @Provides
    fun provideBlockchainStatsApi(retrofit: Retrofit): BlockchainStatsApi {
        return retrofit.create(BlockchainStatsApi::class.java)
    }
}
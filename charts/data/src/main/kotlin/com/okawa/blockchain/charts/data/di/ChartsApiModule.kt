package com.okawa.blockchain.charts.data.di

import com.okawa.blockchain.charts.data.api.BlockchainChartsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ChartsApiModule {

    @Provides
    fun provideBlockchainChartsApi(retrofit: Retrofit): BlockchainChartsApi {
        return retrofit.create(BlockchainChartsApi::class.java)
    }
}
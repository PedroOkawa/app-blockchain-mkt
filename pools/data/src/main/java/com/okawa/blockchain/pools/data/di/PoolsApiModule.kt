package com.okawa.blockchain.pools.data.di

import com.okawa.blockchain.pools.data.api.BlockchainPoolsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PoolsApiModule {

    @Provides
    fun provideBlockchainPoolsApi(retrofit: Retrofit): BlockchainPoolsApi {
        return retrofit.create(BlockchainPoolsApi::class.java)
    }
}
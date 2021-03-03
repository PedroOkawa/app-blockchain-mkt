package com.okawa.blockchain.pools.data.di

import com.okawa.blockchain.pools.data.repository.PoolsRepositoryImpl
import com.okawa.blockchain.pools.domain.repository.PoolsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class PoolsRepositoryModule {

    @Binds
    abstract fun bindsPoolsRepository(poolsRepositoryImpl: PoolsRepositoryImpl): PoolsRepository
}
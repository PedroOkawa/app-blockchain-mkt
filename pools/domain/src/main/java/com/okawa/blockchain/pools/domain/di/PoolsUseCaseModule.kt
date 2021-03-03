package com.okawa.blockchain.pools.domain.di

import com.okawa.blockchain.pools.domain.repository.PoolsRepository
import com.okawa.blockchain.pools.domain.usecase.GetPoolsUseCase
import dagger.Module
import dagger.Provides

@Module
class PoolsUseCaseModule {

    @Provides
    fun bindsGetPoolsUseCase(pollsRepository: PoolsRepository): GetPoolsUseCase {
        return GetPoolsUseCase(pollsRepository)
    }

}
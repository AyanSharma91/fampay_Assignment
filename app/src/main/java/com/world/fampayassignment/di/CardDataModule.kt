package com.world.fampayassignment.di

import com.world.fampayassignment.network.NetworkInterface
import com.world.fampayassignment.repository.CardDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object CardDataModule {

    @Singleton
    @Provides
    fun provideListData(
        networkInterface: NetworkInterface
    ) : CardDataRepository {
        return CardDataRepository(networkInterface)
    }

}
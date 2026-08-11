package com.ricardovalverde.droidchat.data.network.di

import com.ricardovalverde.droidchat.data.network.NetworkDataSource
import com.ricardovalverde.droidchat.data.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {
    @Binds
    @Singleton
    fun bindsNetworkDataSource(impl: NetworkDataSourceImpl): NetworkDataSource
}
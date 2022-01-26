package com.technology.autobuy.di

import com.technology.autobuy.data.base.RetroNetwork
import com.technology.autobuy.data.remote.CarTypeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesReposService(): CarTypeService =
        RetroNetwork().createService(CarTypeService::class.java)
}
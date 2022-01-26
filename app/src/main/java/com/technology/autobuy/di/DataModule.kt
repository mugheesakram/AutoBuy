package com.technology.autobuy.di

import com.technology.autobuy.data.remote.CarTypeApi
import com.technology.autobuy.data.remote.CarTypeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideRemoteRepository(remoteRepository: CarTypeRepository): CarTypeApi
}
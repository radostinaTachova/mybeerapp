package com.example.mybeerapp.data.api

import com.example.mybeerapp.data.BeerInterface
import com.example.mybeerapp.data.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providePunkApiService(): PunkApi {
        return PunkApi.create()
    }


    @Singleton
    @Provides
    fun provideBeerRepository(api: PunkApi): BeerInterface {
        return BeerRepository.getInstance(api)
    }
}

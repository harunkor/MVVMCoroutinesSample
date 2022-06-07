package com.harunkor.mvvmcoroutinessample.di

import com.harunkor.mvvmcoroutinessample.data.local.ApiService
import com.harunkor.mvvmcoroutinessample.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService) : MainRepository {
        return MainRepository(apiService)
    }
}
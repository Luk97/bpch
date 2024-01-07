package com.nickel.bpch.di

import com.nickel.bpch.core.datastore.UserPreferencesDataSource
import com.nickel.bpch.core.domain.OfflineFirstUserDataRepository
import com.nickel.bpch.core.domain.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDataRepository(
        userPreferencesDataSource: UserPreferencesDataSource
    ): UserDataRepository = OfflineFirstUserDataRepository(userPreferencesDataSource)
}
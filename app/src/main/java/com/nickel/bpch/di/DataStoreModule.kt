package com.nickel.bpch.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.nickel.bpch.core.datastore.UserPreferencesSerializer
import com.nickel.bpch.proto.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideAppPreferencesDataSource(
        @ApplicationContext context: Context,
        @ApplicationScope scope: CoroutineScope,
        serializer: UserPreferencesSerializer
        ): DataStore<UserPreferences> =
            DataStoreFactory.create(
                serializer = serializer,
                scope = CoroutineScope(scope.coroutineContext),
            ) {
                context.dataStoreFile("user_preferences.pb")
            }
}
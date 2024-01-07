package com.nickel.bpch.core.domain

import com.nickel.bpch.core.data.UserData
import com.nickel.bpch.core.datastore.UserPreferencesDataSource
import com.nickel.bpch.proto.UserPreferences.ThemeColor
import com.nickel.bpch.proto.UserPreferences.UiMode
import kotlinx.coroutines.flow.Flow

class OfflineFirstUserDataRepository(
    private val userPreferencesDataSource: UserPreferencesDataSource
) : UserDataRepository {

    override val userData: Flow<UserData> = userPreferencesDataSource.userData
    override suspend fun setUiMode(uiMode: UiMode) {
        userPreferencesDataSource.setUiModePreference(uiMode)
    }

    override suspend fun setPrimaryColor(color: ThemeColor) {
        userPreferencesDataSource.setPrimaryColorPreference(color)
    }

    override suspend fun setSecondaryColor(color: ThemeColor) {
        userPreferencesDataSource.setSecondaryColorPreference(color)
    }

    override suspend fun setTertiaryColor(color: ThemeColor) {
        userPreferencesDataSource.setTertiaryColorPreference(color)
    }


}
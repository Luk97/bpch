package com.nickel.bpch.core.datastore

import androidx.datastore.core.DataStore
import com.nickel.bpch.core.data.UserData
import com.nickel.bpch.proto.UserPreferences
import com.nickel.bpch.proto.UserPreferences.ThemeColor
import com.nickel.bpch.proto.UserPreferences.UiMode
import com.nickel.bpch.proto.copy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data
        .map {
            UserData(
                uiMode = it.uiMode,
                primaryColor = it.primaryColor,
                secondaryColor = it.secondaryColor,
                tertiaryColor = it.tertiaryColor
            )
        }

    suspend fun setUiModePreference(uiMode: UiMode) {
        userPreferences.updateData {
            it.copy { this.uiMode = uiMode }
        }
    }

    suspend fun setPrimaryColorPreference(color: ThemeColor) {
        userPreferences.updateData {
            it.copy { this.primaryColor = color }
        }
    }

    suspend fun setSecondaryColorPreference(color: ThemeColor) {
        userPreferences.updateData {
            it.copy { this.secondaryColor = color }
        }
    }

    suspend fun setTertiaryColorPreference(color: ThemeColor) {
        userPreferences.updateData {
            it.copy { this.tertiaryColor = color }
        }
    }
}
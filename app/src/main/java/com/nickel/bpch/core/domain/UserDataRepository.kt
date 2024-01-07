package com.nickel.bpch.core.domain

import com.nickel.bpch.core.data.UserData
import com.nickel.bpch.proto.UserPreferences.ThemeColor
import com.nickel.bpch.proto.UserPreferences.UiMode
import kotlinx.coroutines.flow.Flow


interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun setUiMode(uiMode: UiMode)

    suspend fun setPrimaryColor(color: ThemeColor)

    suspend fun setSecondaryColor(color: ThemeColor)

    suspend fun setTertiaryColor(color: ThemeColor)
}
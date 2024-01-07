package com.nickel.bpch.core.data

import com.nickel.bpch.proto.UserPreferences

data class UserData(
    val uiMode: UserPreferences.UiMode,
    val primaryColor: UserPreferences.ThemeColor,
    val secondaryColor: UserPreferences.ThemeColor,
    val tertiaryColor: UserPreferences.ThemeColor
)
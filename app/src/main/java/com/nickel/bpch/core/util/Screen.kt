package com.nickel.bpch.core.util

sealed class Screen(val route: String, val id: NavigationScreenId) {
    data object LoginScreen: Screen("login_screen", NavigationScreenId.LOGIN_SCREEN)
    data object HomeScreen: Screen("home_screen", NavigationScreenId.HOME_SCREEN)
    data object ProfileScreen: Screen("profile_screen", NavigationScreenId.PROFILE_SCREEN)
    data object SettingsScreen: Screen("settings_screen", NavigationScreenId.SETTINGS_SCREEN)
    data object QuickMatchScreen: Screen("quick_match_screen", NavigationScreenId.QUICK_MATCH_SCREEN)
}

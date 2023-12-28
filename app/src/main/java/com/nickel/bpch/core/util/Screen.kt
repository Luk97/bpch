package com.nickel.bpch.core.util

sealed class Screen(val route: String) {
    data object LoginScreen: Screen("login_screen")
    data object HomeScreen: Screen("home_screen")
    data object ProfileScreen: Screen("profile_screen")
    data object SettingsScreen: Screen("settings_screen")
    data object QuickMatchScreen: Screen("quick_match_screen")
}

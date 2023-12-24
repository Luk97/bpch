package com.nickel.bpch.util

sealed class Screen(val route: String) {
    data object SplashScreen: Screen("splash_screen")
    data object HomeScreen: Screen("home_screen")
}

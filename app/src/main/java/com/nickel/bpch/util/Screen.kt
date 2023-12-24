package com.nickel.bpch.util

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home_screen")
}

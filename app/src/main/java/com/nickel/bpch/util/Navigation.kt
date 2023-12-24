package com.nickel.bpch.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nickel.bpch.ui.home.HomeScreen
import com.nickel.bpch.ui.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) { SplashScreen(navController) }
        composable(Screen.HomeScreen.route) { HomeScreen() }
    }
}
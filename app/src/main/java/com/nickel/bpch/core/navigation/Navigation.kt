package com.nickel.bpch.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nickel.bpch.feature_home.HomeScreen
import com.nickel.bpch.feature_login.ui.LoginScreen
import com.nickel.bpch.feature_profile.ProfileScreen
import com.nickel.bpch.feature_quickmatch.QuickMatchScreen
import com.nickel.bpch.feature_settings.ui.SettingsScreen


@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SettingsScreen.route
    ) {
        composable(Screen.LoginScreen.route) { LoginScreen(navController) }
        composable(Screen.HomeScreen.route) { HomeScreen(navController) }
        composable(Screen.ProfileScreen.route) { ProfileScreen(navController) }
        composable(Screen.QuickMatchScreen.route) { QuickMatchScreen(navController) }
        composable(Screen.SettingsScreen.route) { SettingsScreen(navController) }
    }
}
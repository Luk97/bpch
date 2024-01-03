package com.nickel.bpch.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nickel.bpch.core.navigation.Navigation
import com.nickel.bpch.core.navigation.Screen
import com.nickel.bpch.core.ui.MainViewModel.UiState.Loading
import com.nickel.bpch.core.ui.MainViewModel.UiState.Success
import com.nickel.bpch.core.ui.components.MainScaffold
import com.nickel.bpch.core.ui.theme.BPCHTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainViewModel>()

        splashScreen.setKeepOnScreenCondition {
            when (viewModel.state.value) {
                Loading -> true
                is Success -> false
            }
        }

        setContent {
            BPCHTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val uiState by viewModel.state.collectAsState()
                    when (uiState) {
                        is Success -> {
                            MainScaffold(
                                navController = navController,
                                modifier = Modifier.fillMaxSize(),
                                bottomBarVisible = navBackStackEntry?.destination?.route in listOf(
                                    Screen.HomeScreen.route,
                                    Screen.QuickMatchScreen.route,
                                    Screen.ProfileScreen.route,
                                    Screen.SettingsScreen.route
                                ),
                                items = (uiState as Success).bottomNavigationItems,
                                selectedIndex = (uiState as Success).bottomNavigationIndex,
                                onBottomNavItemClick = viewModel::onBottomNavItemClick
                            ) {
                                Navigation(navController)
                            }
                        }
                        Loading -> Unit
                    }
                }
            }
        }
    }
}
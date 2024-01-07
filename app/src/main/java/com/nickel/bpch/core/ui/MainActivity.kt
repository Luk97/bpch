package com.nickel.bpch.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nickel.bpch.core.navigation.Navigation
import com.nickel.bpch.core.navigation.Screen
import com.nickel.bpch.core.ui.MainViewModel.UiState.Loading
import com.nickel.bpch.core.ui.MainViewModel.UiState.Success
import com.nickel.bpch.core.ui.components.MainScaffold
import com.nickel.bpch.core.ui.theme.BPCHTheme
import com.nickel.bpch.proto.UserPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainViewModel>()
        var uiState: MainViewModel.UiState by mutableStateOf(Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state
                    .onEach { uiState = it }
                    .collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                Loading -> true
                is Success -> false
            }
        }


        setContent {
            val darkTheme = shouldUseDarkTheme(uiState)
            when (uiState) {
                is Success -> {
                    BPCHTheme(
                        darkTheme = darkTheme,
                        primaryColor = (uiState as Success).userData.primaryColor,
                        secondaryColor = (uiState as Success).userData.secondaryColor,
                        tertiaryColor = (uiState as Success).userData.tertiaryColor
                        ) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            val navController = rememberNavController()
                            val navBackStackEntry by navController.currentBackStackEntryAsState()


                            MainScaffold(
                                navController = navController,
                                modifier = Modifier.fillMaxSize(),
                                bottomBarVisible = navBackStackEntry?.destination?.route in listOf(
                                    Screen.HomeScreen.route,
                                    Screen.QuickMatchScreen.route,
                                    Screen.ProfileScreen.route,
                                    Screen.SettingsScreen.route
                                ),
                                //items = (uiState as Success).bottomNavigationItems,
                                //selectedIndex = (uiState as Success).bottomNavigationIndex,
                                onBottomNavItemClick = viewModel::onBottomNavItemClick
                            ) {
                                Navigation(navController)
                            }
                        }

                    }
                }
                Loading -> Unit
            }
        }
    }
}

@Composable
private fun shouldUseDarkTheme(uiState: MainViewModel.UiState): Boolean =
    when (uiState) {
        Loading -> isSystemInDarkTheme()
        is Success -> when (uiState.userData.uiMode) {
            UserPreferences.UiMode.LIGHT -> false
            UserPreferences.UiMode.DARK -> true
            UserPreferences.UiMode.SYSTEM -> isSystemInDarkTheme()
            else -> isSystemInDarkTheme()
        }
    }

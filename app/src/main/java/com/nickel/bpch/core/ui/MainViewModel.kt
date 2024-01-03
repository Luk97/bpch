package com.nickel.bpch.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.StarOutline
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickel.bpch.core.navigation.BottomNavigationItem
import com.nickel.bpch.core.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.nickel.bpch.core.ui.MainViewModel.UiState.Loading
import com.nickel.bpch.core.ui.MainViewModel.UiState.Success
import kotlinx.coroutines.flow.update

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow<UiState>(Loading)
    val state = _state.asStateFlow()



    init {
        viewModelScope.launch {
            delay(1000L)
            _state.update { Success() }
        }
    }

    fun onBottomNavItemClick(index: Int) {
        when (_state.value) {
            is Success -> _state.update { Success(
                bottomNavigationIndex = index
            ) }
            else -> Unit
        }
    }

    sealed interface UiState {
        data object Loading: UiState
        data class Success(
            val bottomNavigationItems: List<BottomNavigationItem> = listOf(
                BottomNavigationItem(
                    label = "Home",
                    route = Screen.HomeScreen.route,
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                ),
                BottomNavigationItem(
                    label = "Quick Match",
                    route = Screen.QuickMatchScreen.route,
                    selectedIcon = Icons.Filled.Star,
                    unselectedIcon = Icons.Outlined.StarOutline,
                ),
                BottomNavigationItem(
                    label = "Profile",
                    route = Screen.ProfileScreen.route,
                    selectedIcon = Icons.Filled.Person,
                    unselectedIcon = Icons.Outlined.Person,
                    badgeCount = 42
                ),
                BottomNavigationItem(
                    label = "Settings",
                    route = Screen.SettingsScreen.route,
                    selectedIcon = Icons.Filled.Settings,
                    unselectedIcon = Icons.Outlined.Settings,
                    badgeCount = 0
                )
            ),
            val bottomNavigationIndex: Int = 0
        ): UiState
    }
}
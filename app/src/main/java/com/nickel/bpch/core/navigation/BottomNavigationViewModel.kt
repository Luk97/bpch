package com.nickel.bpch.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickel.bpch.core.util.UiEvent
import com.nickel.bpch.core.util.UiEvent.Navigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(
        UiState(
            navigationItems = listOf(
                BottomNavigationItem(
                    title = "Home",
                    route = Screen.HomeScreen.route,
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    hasNews = false
                ),
                BottomNavigationItem(
                    title = "Quick Match",
                    route = Screen.QuickMatchScreen.route,
                    selectedIcon = Icons.Filled.Star,
                    unselectedIcon = Icons.Outlined.Star,
                    hasNews = false
                ),
                BottomNavigationItem(
                    title = "Profile",
                    route = Screen.ProfileScreen.route,
                    selectedIcon = Icons.Filled.Person,
                    unselectedIcon = Icons.Outlined.Person,
                    hasNews = false,
                    badgeCount = 42
                ),
                BottomNavigationItem(
                    title = "Settings",
                    route = Screen.SettingsScreen.route,
                    selectedIcon = Icons.Filled.Settings,
                    unselectedIcon = Icons.Outlined.Settings,
                    hasNews = true
                )
            ),
        )
    )
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    fun onItemClick(index: Int) {
        viewModelScope.launch {
            _event.emit(
                Navigate(
                    route = _state.value.navigationItems[index].route
                )
            )
        }
    }

    data class UiState(
        val navigationItems: List<BottomNavigationItem>,
    )
}

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)


package com.nickel.bpch.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(
        UiState(
            navigationItems = listOf(
                BottomNavigationItem(
                    title = "Home",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    hasNews = false
                ),
                BottomNavigationItem(
                    title = "Friends",
                    selectedIcon = Icons.Filled.People,
                    unselectedIcon = Icons.Outlined.People,
                    hasNews = false,
                    badgeCount = 42
                ),
                BottomNavigationItem(
                    title = "Quick Match",
                    selectedIcon = Icons.Filled.Star,
                    unselectedIcon = Icons.Outlined.Star,
                    hasNews = false
                ),
                BottomNavigationItem(
                    title = "Settings",
                    selectedIcon = Icons.Filled.Settings,
                    unselectedIcon = Icons.Outlined.Settings,
                    hasNews = true
                )
            ),
            selectedIndex = 0
        )
    )
    val state = _state.asStateFlow()

    fun onItemClick(index: Int) {
        _state.update { it.copy(selectedIndex = index) }
        //TODO: navigate to screen
    }

    data class UiState(
        val navigationItems: List<BottomNavigationItem>,
        val selectedIndex: Int
    )
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)


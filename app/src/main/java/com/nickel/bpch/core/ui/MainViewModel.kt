package com.nickel.bpch.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickel.bpch.core.data.UserData
import com.nickel.bpch.core.domain.UserDataRepository
import com.nickel.bpch.core.ui.MainViewModel.UiState.Loading
import com.nickel.bpch.core.ui.MainViewModel.UiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userDataRepository: UserDataRepository
): ViewModel() {

    val state: StateFlow<UiState> = userDataRepository.userData.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun onBottomNavItemClick(index: Int) {
        /*
        when (state.value) {
            is Success -> state.value.update { Success(
                bottomNavigationIndex = index
            ) }
            else -> Unit
        }
         */
    }

    sealed interface UiState {
        data object Loading: UiState
        data class Success(
            val userData: UserData,
            /*
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
            */
        ): UiState
    }
}
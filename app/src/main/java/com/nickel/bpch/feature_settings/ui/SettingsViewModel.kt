package com.nickel.bpch.feature_settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickel.bpch.core.data.UserData
import com.nickel.bpch.core.domain.UserDataRepository
import com.nickel.bpch.feature_settings.ui.SettingsViewModel.UiState.Loading
import com.nickel.bpch.feature_settings.ui.SettingsViewModel.UiState.Success
import com.nickel.bpch.proto.UserPreferences.ThemeColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
): ViewModel() {

    val state: StateFlow<UiState> = userDataRepository.userData.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun changeToCyan() {
        viewModelScope.launch {
            userDataRepository.setPrimaryColor(ThemeColor.CYAN)
        }
    }

    fun changeToOrange() {
        viewModelScope.launch {
            userDataRepository.setPrimaryColor(ThemeColor.ORANGE)
        }
    }

    fun changeToPurple() {
        viewModelScope.launch {
            userDataRepository.setPrimaryColor(ThemeColor.PURPLE)
        }
    }

    sealed interface UiState {
        data object Loading: UiState
        data class Success(
            val userData: UserData
        ): UiState
    }
}
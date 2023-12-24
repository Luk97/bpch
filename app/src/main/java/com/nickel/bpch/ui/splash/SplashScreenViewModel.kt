package com.nickel.bpch.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickel.bpch.ui.splash.UiState.Navigating
import com.nickel.bpch.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableSharedFlow<UiState>()
    val state = _state.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _state.emit(
                Navigating(Screen.HomeScreen.route)
            )
        }
    }
}

sealed interface UiState {
    data class Navigating(val route: String): UiState
}


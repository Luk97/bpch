package com.nickel.bpch.feature_login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickel.bpch.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    fun onEmailValueChange(content: String) {
        _state.update { it.copy(email = content) }
    }

    fun onPasswordValueChange(content: String) {
        _state.update { it.copy(password = content) }
    }

    fun onPasswordVisibilityChange() {
        _state.update { it.copy(passwordVisible = !_state.value.passwordVisible) }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _event.emit(UiEvent.Navigate("home_screen"))
        }
    }
    data class UiState(
        val email: String = "",
        val password: String = "",
        val passwordVisible: Boolean = false
    )
}
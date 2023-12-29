package com.nickel.bpch.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            _state.update { Success }
        }
    }

    sealed interface UiState {
        data object Loading: UiState
        data object Success: UiState
    }
}
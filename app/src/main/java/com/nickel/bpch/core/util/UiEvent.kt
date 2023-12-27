package com.nickel.bpch.core.util

sealed interface UiEvent {
    data class Navigate(val route: String): UiEvent
}
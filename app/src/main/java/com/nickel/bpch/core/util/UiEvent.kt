package com.nickel.bpch.core.util

sealed interface UiEvent {
    data class Navigate(val route: String, val popStack: Boolean = true): UiEvent
}
package com.nickel.bpch.core.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationRepository {
    private val _bottomNavSelectedIndex = MutableStateFlow(0)
    val bottomNavSelectedIndex = _bottomNavSelectedIndex.asStateFlow()

    fun updateBottomNavSelectedIndex(index: Int) {
        _bottomNavSelectedIndex.value = index
    }
}
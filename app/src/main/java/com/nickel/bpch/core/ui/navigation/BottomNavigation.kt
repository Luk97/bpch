package com.nickel.bpch.core.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nickel.bpch.core.util.UiEvent.Navigate
import kotlinx.coroutines.flow.collectLatest


@Composable
fun BottomNavigation(
    navController: NavController,
    viewModel: BottomNavigationViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsState()

    val selectedIndex = uiState.navigationItems.indexOf(
        uiState.navigationItems.find { it.route == navController.currentDestination?.route }
    )


    LaunchedEffect(key1 = Unit) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is Navigate -> {
                    if (event.route != navController.currentDestination?.route) {
                        navController.popBackStack()
                        navController.navigate(event.route)
                    }
                }
                else -> {}
            }
        }
    }

    BottomNavigationBar(
        items = uiState.navigationItems,
        selectedIndex = selectedIndex,
        onItemClick = viewModel::onItemClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onItemClick(index) },
                label = { Text(text = item.title) },
                alwaysShowLabel = false,
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selectedIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        items = listOf(
            BottomNavigationItem(
                title = "Home",
                route = "",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                hasNews = false
            ),
            BottomNavigationItem(
                title = "Profile",
                route = "",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person,
                hasNews = false,
                badgeCount = 42
            ),
            BottomNavigationItem(
                title = "Settings",
                route = "",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                hasNews = true
            )
        ),
        selectedIndex = 0,
        onItemClick = {}
    )
}
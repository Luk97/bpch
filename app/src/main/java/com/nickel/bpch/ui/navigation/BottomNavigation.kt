package com.nickel.bpch.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun BottomNavigation(
    viewModel: BottomNavigationViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsState()

    BottomNavigationBar(
        items = uiState.navigationItems,
        selectedItemIndex = uiState.selectedIndex,
        onItemClick = viewModel::onItemClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
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
                            imageVector = if (selectedItemIndex == index) {
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
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                hasNews = true
            )
        ),
        selectedItemIndex = 0,
        onItemClick = {}
    )
}
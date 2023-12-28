package com.nickel.bpch.core.navigation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nickel.bpch.core.ui.theme.BPCHTheme
import com.nickel.bpch.core.ui.theme.SoftRed
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
                        if (event.popStack) navController.popBackStack()
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
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 10.dp
    ) {
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
                                Badge(
                                    containerColor = SoftRed,
                                    contentColor = MaterialTheme.colorScheme.onBackground
                                ) {
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
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onBackground,
                    selectedTextColor = MaterialTheme.colorScheme.onBackground,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}

@Preview
@Composable
private fun LightModePreview() {
    BPCHTheme {
        BottomNavigationBar(
            items = previewList,
            selectedIndex = 0,
            onItemClick = {}
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DarkModePreview() {
    BPCHTheme {
        BottomNavigationBar(
            items = previewList,
            selectedIndex = 0,
            onItemClick = {}
        )
    }
}

private val previewList = listOf(
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
)
package com.nickel.bpch.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nickel.bpch.core.navigation.BottomNavigationItem
import com.nickel.bpch.core.ui.theme.BPCHTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    bottomBarVisible: Boolean = false,
    items: List<BottomNavigationItem> = emptyList(),
    selectedIndex: Int = 0,
    onBottomNavItemClick: (Int) -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (bottomBarVisible) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    items.forEachIndexed { index, item ->
                        MainNavigationItem(
                            item = item,
                            modifier = modifier,
                            selected = index == selectedIndex,
                            onClick = {
                                if (index != selectedIndex) {
                                    onBottomNavItemClick(index)
                                    navController.popBackStack()
                                    navController.navigate(item.route)
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        it.calculateBottomPadding()
        content()
    }
}

@Preview
@Composable
private fun LightModePreview() {
    BPCHTheme {
        MainScaffold(
            navController = rememberNavController(),
            items = listOf(
                BottomNavigationItem(
                    label = "Home",
                    route = "",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                ),
                BottomNavigationItem(
                    label = "Profile",
                    route = "",
                    selectedIcon = Icons.Filled.Person,
                    unselectedIcon = Icons.Outlined.Person,
                    badgeCount = 42
                ),
                BottomNavigationItem(
                    label = "Settings",
                    route = "",
                    selectedIcon = Icons.Filled.Settings,
                    unselectedIcon = Icons.Outlined.Settings,
                )
            ),
            bottomBarVisible = true
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DarkModePreview() {
    BPCHTheme {
        MainScaffold(
            navController = rememberNavController(),
            items = listOf(
                BottomNavigationItem(
                    label = "Home",
                    route = "",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                ),
                BottomNavigationItem(
                    label = "Profile",
                    route = "",
                    selectedIcon = Icons.Filled.Person,
                    unselectedIcon = Icons.Outlined.Person,
                    badgeCount = 42
                ),
                BottomNavigationItem(
                    label = "Settings",
                    route = "",
                    selectedIcon = Icons.Filled.Settings,
                    unselectedIcon = Icons.Outlined.Settings,
                )
            ),
            bottomBarVisible = true
        )
    }
}



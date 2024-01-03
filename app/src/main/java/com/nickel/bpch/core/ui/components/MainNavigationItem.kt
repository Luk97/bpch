package com.nickel.bpch.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nickel.bpch.core.navigation.BottomNavigationItem
import com.nickel.bpch.core.ui.theme.SoftRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.MainNavigationItem(
    item: BottomNavigationItem,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        label = { Text(text = item.label) },
        alwaysShowLabel = false,
        onClick = onClick,
        icon = {
            BadgedBox(
                badge = {
                    if (item.badgeCount != null) {
                        when(item.badgeCount) {
                            0 -> Badge(containerColor = SoftRed)
                            else -> Badge(
                                containerColor = SoftRed,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ) {
                                Text(text = item.badgeCount.toString())
                            }
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = if (selected) {
                        item.selectedIcon
                    } else item.unselectedIcon,
                    contentDescription = item.label
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
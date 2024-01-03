package com.nickel.bpch.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nickel.bpch.core.ui.theme.BPCHTheme

@Composable
fun ScreenTopBar(
    navController: NavController,
) {}

@Preview
@Composable
private fun LightMode() {
    BPCHTheme {
        ScreenTopBar(rememberNavController())
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() {
    BPCHTheme {
        ScreenTopBar(rememberNavController())
    }
}
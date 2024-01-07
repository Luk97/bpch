package com.nickel.bpch.core.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.nickel.bpch.proto.UserPreferences.ThemeColor

@Composable
fun BPCHTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    primaryColor: ThemeColor = ThemeColor.CYAN,
    secondaryColor: ThemeColor = ThemeColor.ORANGE,
    tertiaryColor: ThemeColor = ThemeColor.PURPLE,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> createDarkColorScheme(primaryColor, secondaryColor, tertiaryColor)
        else -> createLightColorScheme(primaryColor, secondaryColor, tertiaryColor)
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
            // TODO: check if system top and bot bar should be primary color
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
private fun createLightColorScheme(
    primary: ThemeColor,
    secondary: ThemeColor,
    tertiary: ThemeColor
): ColorScheme = lightColorScheme(
    primary = when(primary) {
        ThemeColor.ORANGE -> Orange
        ThemeColor.PURPLE -> Purple
        else -> Cyan
    },
    secondary = when(secondary) {
        ThemeColor.CYAN -> Cyan
        ThemeColor.PURPLE -> Purple
        else -> Orange
    },
    tertiary = when(tertiary) {
        ThemeColor.CYAN -> Cyan
        ThemeColor.ORANGE -> Orange
        else -> Purple
    },
    background = White,
    onBackground = DarkGray,
    onPrimary = DarkGray
)

@Composable
private fun createDarkColorScheme(
    primary: ThemeColor,
    secondary: ThemeColor,
    tertiary: ThemeColor
): ColorScheme = darkColorScheme(
    primary = when(primary) {
        ThemeColor.ORANGE -> Orange
        ThemeColor.PURPLE -> Purple
        else -> Cyan
    },
    secondary = when(secondary) {
        ThemeColor.CYAN -> Cyan
        ThemeColor.PURPLE -> Purple
        else -> Orange
    },
    tertiary = when(tertiary) {
        ThemeColor.CYAN -> Cyan
        ThemeColor.ORANGE -> Orange
        else -> Purple
    },
    background = DarkGray,
    onBackground = White,
    onPrimary = White
)
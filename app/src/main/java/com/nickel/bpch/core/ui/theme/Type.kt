package com.nickel.bpch.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nickel.bpch.R

val carlito = FontFamily(
    Font(R.font.carlito_regular, FontWeight.Normal),
    Font(R.font.carlito_italic, FontWeight.Light),
    Font(R.font.carlito_bold, FontWeight.Bold),
    Font(R.font.carlito_bold_italic, FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = carlito,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = carlito,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = carlito,
        fontWeight = FontWeight.Bold,
        fontSize = 54.sp,
        lineHeight = 42.sp,
        letterSpacing = 0.5.sp
    ),
)
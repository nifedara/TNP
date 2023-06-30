package com.example.tradeline.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = Blue1,
    onPrimary = White99,
    primaryContainer = Black,
    onPrimaryContainer = Blue2,
    secondary = White98,
    onSecondary = Grey98,
    secondaryContainer = Cream99,
    onSecondaryContainer = Grey99,
    tertiary = White97,
    onTertiary = Green99,
    tertiaryContainer = Grey97,
    onTertiaryContainer = White96,
    outline = White95,
    errorContainer = White94,
    onErrorContainer = Red
)

private val LightColorPalette = lightColorScheme(
    primary = White99,
    onPrimary = Blue1,
    primaryContainer = Black,
    onPrimaryContainer = Blue2,
    secondary = White98,
    onSecondary = Grey98,
    secondaryContainer = Cream99,
    onSecondaryContainer = Grey99,
    tertiary = White97,
    onTertiary = Green99,
    tertiaryContainer = Grey97,
    onTertiaryContainer = White96,
    outline = White95,
    errorContainer = White94,
    onErrorContainer = Red,
    surface = White
)

@Composable
fun TradelineTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
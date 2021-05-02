package com.example.composedemo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = white,
    primaryVariant = white,
    secondary = rust300,
    background = gray900,
    surface = whiteOp15,
    onPrimary = gray900,
    onSecondary = gray900,
    onBackground = taupe100,
    onSurface = whiteOp80
)

private val LightColorPalette = lightColors(
    primary = gray900,
    primaryVariant = gray800,
    secondary = rust600,
    background = taupe100,
    surface = whiteOp85,
    onPrimary = white,
    onSecondary = white,
    onBackground = taupe800,
    onSurface = gray800
)


@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

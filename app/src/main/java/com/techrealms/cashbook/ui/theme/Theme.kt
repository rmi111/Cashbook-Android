package com.techrealms.cashbook.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
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

private val darkColorScheme = darkColorScheme(
    background = NeutralDarkOne,
    surface = NeutralGreyOne,
    primary = PrimaryBrand,
    onPrimary = NeutralWhite,
    primaryContainer = NeutralDarkOne,
    onPrimaryContainer = NeutralWhite,
    secondary = PrimaryLight,
    tertiary = NeutralGreyFour,
    onBackground = NeutralWhite,
    onTertiaryContainer = NeutralWhite,
    onSurface = PrimaryBrand,
    outline = NeutralGreyFive,
)

private val lightColorScheme = lightColorScheme(
    primary = PrimaryBrand,
    onPrimary = NeutralWhite,
    primaryContainer = NeutralDarkOne,
    onPrimaryContainer = NeutralGreyOne,
    secondary = PrimaryLight,
    tertiary = NeutralGreyFour,
    onTertiaryContainer = NeutralDarkOne,
    onSurface = NeutralGreyOne,
    //onBackground = Color.White,
   // background = NeutralWhite,
    surface = NeutralDarkOne,
    outline = NeutralGreyFive,

//    primary = Color.Red,
//    onPrimary = Color.Red,
//    onSecondary = Color.Red,
//    onTertiaryContainer = Color.Red,
//    onTertiary = Color.Red,
//    onBackground = Color.White,
//    onSurface = Color.Red,
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CashBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            window.navigationBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
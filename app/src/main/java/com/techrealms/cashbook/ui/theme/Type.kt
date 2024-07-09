package com.techrealms.cashbook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.techrealms.cashbook.R

//val fonts = FontFamily(
//    Font(R.font.hey_wow_regular),
//    Font(R.font.hey_wow_bold, weight = FontWeight.Bold),
//    Font(R.font.hey_wow_light, weight = FontWeight.Light),
//    Font(R.font.hey_wow_medium, weight = FontWeight.Medium),
//    Font(R.font.hey_wow_semi_bold, weight = FontWeight.SemiBold),
//    Font(R.font.hey_wow_extra_bold, weight = FontWeight.ExtraBold),
//
//)


// Declare the font families
object AppFont {
    val fonts = FontFamily(
        Font(R.font.hey_wow_regular),
        Font(R.font.hey_wow_medium, style = FontStyle.Italic),
        Font(R.font.hey_wow_light, weight = FontWeight.Light),
        Font(R.font.hey_wow_medium, weight = FontWeight.Medium),
        Font(R.font.hey_wow_semi_bold, weight = FontWeight.SemiBold),
        Font(R.font.hey_wow_extra_bold, weight = FontWeight.ExtraBold),
    )
}

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.fonts),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.fonts),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.fonts),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.fonts),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.fonts),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.fonts),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.fonts),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.fonts),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.fonts),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.fonts),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.fonts),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.fonts),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.fonts),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.fonts),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.fonts)
)

// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = fonts,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//    /* Other default text styles to override
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//    */
//)
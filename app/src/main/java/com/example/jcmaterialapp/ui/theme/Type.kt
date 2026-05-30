package com.example.jcmaterialapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jcmaterialapp.R

val bodyFontFamily = FontFamily(
    Font(R.font.winky_rough_regular)
)
val titleLargeFontFamily = FontFamily(
    Font(
        R.font.winky_rough_italic)
)

@OptIn(ExperimentalTextApi::class)
val flexFontFamily = FontFamily(
    Font(
        R.font.roboto_flex,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(900),
            FontVariation.width(50f),
            FontVariation.slant(-30f)
        )
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = flexFontFamily,
        fontSize = 13.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = flexFontFamily,
    ),
    bodyLarge = TextStyle(
        fontFamily = bodyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = flexFontFamily,
        fontSize = 28.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = titleLargeFontFamily,
        fontSize = 20.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.5.sp
    )
)


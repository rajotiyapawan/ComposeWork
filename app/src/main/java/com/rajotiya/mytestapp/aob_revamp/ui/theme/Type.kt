package com.rajotiya.mytestapp.aob_revamp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont

// Set of Material typography styles to start with
val AoBTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
        fontSize = 20.sp,
        lineHeight = 32.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
        fontSize = 14.sp,
        lineHeight = 16.sp
    )
)
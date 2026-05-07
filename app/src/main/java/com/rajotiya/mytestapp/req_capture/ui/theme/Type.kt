package com.rajotiya.mytestapp.req_capture.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont

val ReqCaptureTypography = Typography(
    headlineSmall = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
        fontSize = 14.sp,
        lineHeight = 16.sp
    )
)

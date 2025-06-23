package com.rajotiya.mytestapp.random

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 01-05-2025.
 */

@Composable
fun MbPrimeFreeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(color = Color.White)) {
        Image(
            painter = painterResource(R.drawable.vector_city_bg),
            contentDescription = null, contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        Column(
            modifier
                .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
                        .size(16.dp)
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp), contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(R.drawable.congrats_svg), contentDescription = null)
            }
            Text(
                "As a valued member, we’re upgrading you to",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                color = textColorDark,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_prime_bitmap_chat),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_BOLD))) {
                            append("MB Prime")
                        }
                        append(" for Free")
                    },
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                    color = textColorDark
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp), contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .border(
                            width = 1.dp,
                            brush = Brush.verticalGradient(colors = listOf(Color(0xffffebb3), Color(0xffab762e))),
                            shape = RoundedCornerShape(50)
                        )
                        .background(
                            brush = Brush.verticalGradient(colors = listOf(Color(0xab762e00), Color(0xff453013))),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(vertical = 4.dp, horizontal = 16.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Exclusively for select few members!",
                        color = Color(0xffab762e),
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                        fontSize = 14.sp,
                        lineHeight = 24.sp
                    )
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 42.dp), contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .width(45.dp)
                        .height(1.dp)
                        .background(color = textColorDark)
                )
                Box(
                    Modifier
                        .size(10.dp)
                        .border(color = textColorDark, width = 1.dp, shape = CircleShape)
                        .background(color = Color.White, shape = CircleShape)
                )
            }
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 13.dp)
                        .border(
                            width = 1.dp,
                            brush = Brush.verticalGradient(colors = listOf(Color(0xffeb860d), Color(0xffffebb3))),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(top = 27.dp, end = 13.dp, bottom = 20.dp, start = 20.dp)
                ) {
                    Row {
                        Icon(Icons.Default.Check, contentDescription = null)
                        Text(
                            "Access MB Prime exclusive properties",
                            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                            fontSize = 14.sp,
                            lineHeight = 24.sp,
                            color = textColorDark
                        )
                    }
                    Row {
                        Icon(Icons.Default.Check, contentDescription = null)
                        Text(
                            buildAnnotatedString {
                                append("Get contact no. of 10 Owners ")
                                withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                                    append("(Max 1 per day)")
                                }
                            },
                            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                            fontSize = 14.sp,
                            lineHeight = 24.sp,
                            color = textColorDark
                        )
                    }
                }
                InwardConeBorderBox(
                    Modifier
                        .wrapContentWidth(unbounded = true)
                        .height(IntrinsicSize.Min)
                        .padding(horizontal = 16.dp),
                    borderColor = Color(0xffeb860d), backgroundColor = Color.White
                ) {
                    Text(
                        "Membership Benefits",
                        fontSize = 12.sp,
                        lineHeight = 24.sp, maxLines = 1,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                        color = Color(0xffeb860d)
                    )
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(horizontal = 24.dp), contentAlignment = Alignment.Center
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = mbRed)) {
                            append("Note:")
                        }
                        append(" If you don’t use your 1 Free Contact on any day, it will expire.")
                    },
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = textColorDark,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
                )
            }
            Box(
                Modifier
                    .padding(top = 20.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(50))
                    .padding(vertical = 12.dp, horizontal = 24.dp)
            ) {
                Text("OK", color = Color.White, fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD), fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun InwardConeBorderBox(
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Black,
    backgroundColor: Color = Color.Transparent,
    cornerCut: Dp = 6.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val cutPx = with(LocalDensity.current) { cornerCut.toPx() }

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                moveTo(0f, 0f) // Top left
                lineTo(width, 0f) // Top right
                lineTo(width - cutPx, height / 2) // Mid right
                lineTo(width, height) // Bottom right
                lineTo(0f, height) // Bottom left
                lineTo(cutPx, height / 2) // Mid left
                close()
            }

            drawPath(path, color = backgroundColor)
            drawPath(path, color = borderColor, style = Stroke(width = 1.dp.toPx()))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), // Optional padding inside the shape
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}


@Preview
@Composable
private fun TestPrimeScreen() {
    MbPrimeFreeScreen(modifier = Modifier.fillMaxSize())
}
package com.rajotiya.mytestapp.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbGreen
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.utility.CustomModalBottomSheet
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

/**
 * Created by Pawan Rajotiya on 12-02-2026.
 */

@Preview
@Composable
private fun TestPopup() {
    AppWidgetPopup(
        Modifier
            .fillMaxWidth()
            .background(color = Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppWidgetPopup(modifier: Modifier = Modifier) {
    CustomModalBottomSheet({}) {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(32.dp))
            Text(
                buildAnnotatedString {
                    append("Add a ")
                    withStyle(SpanStyle(color = mbGreen, fontFamily = getFontFamily(weight = FontWeight.Bold))) {
                        append("Magicbricks shortcut ")
                    }
                    append("to your home screen")
                },
                fontSize = 12.sp,
                lineHeight = 28.sp,
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                color = textColorDark
            )
            Text(
                "Get quick access to Buyer Enquiries",
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                color = textColorDark
            )
            Spacer(Modifier.height(32.dp))
            Box(Modifier.weight(1f)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 44.dp)
                        .border(width = 5.dp, color = Color(0xffd7d7d7), shape = RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    Image(
                        painter = painterResource(R.drawable.phone_background),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    AppWidgetView(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .padding(horizontal = 19.dp)
                    )
                }

                Box(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color(0xfff5f5f5))
                        .padding(vertical = 20.dp, horizontal = 28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(mbRed, RoundedCornerShape(50))
                            .noRippleClick {}
                            .padding(vertical = 13.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Add Shortcut to Home",
                            color = Color.White,
                            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AppWidgetView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(28.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.frame_magic),
                contentDescription = "Logo",
                modifier = Modifier.height(16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(contentAlignment = Alignment.CenterEnd) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(end = 12.dp)
                        .background(Color.Black, RoundedCornerShape(50.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontFamily = getFontFamily(weight = FontWeight.SemiBold))) {
                                append("2")
                            }
                            append(" New Enquiries")
                        },
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color.White,
                            fontFamily = getFontFamily()
                        )
                    )
                }
                Image(
                    painter = painterResource(R.drawable.bell_gold),
                    contentDescription = "Bell Icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(14.dp))
                .padding(start = 12.dp, top = 8.dp, end = 6.dp, bottom = 8.dp)
        ) {
            Text(
                "Sanyam Khurana (buyer)",
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                color = textColorDark
            )
            Spacer(Modifier.height(3.dp))
            Text(
                "Interested in:",
                fontSize = 10.sp,
                fontFamily = getFontFamily(),
                color = Color.Black
            )
            Spacer(Modifier.height(3.dp))
            Text(
                "3 BHK | ₹ 3.5 cr | Godrej Aristocrat, Sector 10, Greater Noida West",
                fontSize = 10.sp,
                fontFamily = getFontFamily(),
                color = textColorDark
            )
        }
        Spacer(Modifier.height(3.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = mbRed, shape = RoundedCornerShape(50))
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Show All Responses", color = mbRed, fontSize = 13.sp, fontFamily = getFontFamily(weight = FontWeight.SemiBold))
        }
    }
}
package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont

@Composable
fun LoyaltySVBookingDailog(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 23.dp, topEnd = 23.dp))
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(bottom = 85.dp)
        ) {
            TopView()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                    .background(color = Color(0xfff5f5f5))
            )
            SVScheduleView()
        }
        BottomView(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun BottomView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 23.dp, topEnd = 23.dp))
            .drawBehind {
                drawShadowGradient()
            }
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(topStart = 23.dp, topEnd = 23.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        BottomRedButton(enable = true, ctaText = "Select Properties", onClick = {})
    }

}

fun DrawScope.drawShadowGradient() {
    drawRect(
        brush = Brush.verticalGradient(
            colors = listOf(
                Color(
                    0x19000000
                ), // Shadow color
                Color.Transparent // No shadow
            )
        )
    )
}

@Composable
private fun BottomRedButton(
    modifier: Modifier = Modifier,
    enable: Boolean,
    ctaText: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { if (enable) onClick() }
            .background(color = Color(0xffd8232a), shape = RoundedCornerShape(50))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ctaText,
                color = if (enable) Color.White else Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null,
                tint = Color.White,
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SVScheduleView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            "When would you prefer to visit properties?",
            fontSize = 14.sp,
            color = Color(0xff303030),
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 19.dp)
        ) {
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")
            SVScheduleItem("Today")


        }
    }
}

@Composable
private fun SVScheduleItem(str: String) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp, bottom = 8.dp)
            .border(width = 1.dp, color = Color(0xfff5f5f5), shape = RoundedCornerShape(16.dp))
            .padding(end = 12.dp, start = 12.dp)
    ) {
        Text(
            str,
            fontSize = 14.sp,
            color = Color(0xff303030),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 7.dp),
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
        )
    }
}

@Composable
private fun TopView() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.loyalty_landing_bg), contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 23.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val annotatedStringFreeCabPickupDrop = buildAnnotatedString {
                append(
                    AnnotatedString(
                        text = "FREE Cab",
                        spanStyle = SpanStyle(
                            color = Color(0xff009681),
                        )
                    )
                )
                append(" Pickup & Drop")
            }
            Spacer(Modifier.height(18.dp))
            Text(
                annotatedStringFreeCabPickupDrop,
                fontSize = 16.sp,
                color = Color(0xff303030),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            Text(
                "Service is Activated for you",
                fontSize = 16.sp,
                color = Color(0xff303030),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                modifier = Modifier.padding(top = 4.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_loader_image_3), contentDescription = null,
                modifier = Modifier.padding(vertical = 27.dp)
            )
        }
    }

}


@Preview
@Composable
private fun PreviewLoyaltySVBookingDailog() {
    LoyaltySVBookingDailog(modifier = Modifier.fillMaxWidth())
}
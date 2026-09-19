package com.rajotiya.mytestapp.itinerary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.outlined.AccessAlarm
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbGreen
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.utility.dottedLine
import com.rajotiya.mytestapp.utility.getFontFamily

@Preview
@Composable
private fun ConfirmationScreenTest() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xfff9f9f9))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConfirmationHeader(Modifier)
            ConfirmationBody(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            ConfirmationFooter(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }
        Spacer(Modifier.weight(1f))
        ConfirmationBottomView(Modifier.fillMaxWidth())
    }
}

@Composable
fun ConfirmationBottomView(modifier: Modifier = Modifier) {
    Box(
        modifier
            .dropShadow(shape = RectangleShape, shadow = Shadow(radius = 12.dp, color = Color(0x0c000000)))
            .border(width = 1.dp, color = Color(0xffe8eaed), shape = RectangleShape)
            .background(color = Color.White)
            .padding(12.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color(0xffd7d7d7), shape = RoundedCornerShape(50))
                .padding(vertical = 11.dp), contentAlignment = Alignment.Center
        ) {
            Text("Got it", color = textColorLight, fontSize = 14.sp, lineHeight = 20.sp, fontFamily = getFontFamily(weight = FontWeight.SemiBold))
        }
    }
}

@Composable
fun ConfirmationHeader(modifier: Modifier = Modifier) {
    val headerShape = RoundedCornerShape(50)
    Row(
        modifier
            .border(width = 1.dp, color = Color(0xffe0f2f0), shape = headerShape)
            .background(color = Color(0xffedfaf9), shape = headerShape)
            .padding(vertical = 6.dp, horizontal = 12.dp)
    ) {
        Image(painter = painterResource(R.drawable.ic_tick_green), contentDescription = null, modifier = Modifier.size(12.dp))
        Spacer(Modifier.width(7.dp))
        Text("Site Visit Confirmed", color = mbGreen, fontFamily = getFontFamily(weight = FontWeight.Bold), fontSize = 9.sp)
    }
}

@Composable
fun ConfirmationBody(modifier: Modifier = Modifier) {
    Column(
        modifier
            .border(width = 1.dp, color = Color(0xffe5e7eb), shape = RoundedCornerShape(20.dp))
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
    ) {
        Text(
            "Site Visit Details",
            color = textColorDark,
            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 20.dp, top = 12.dp, bottom = 6.dp, end = 20.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .background(color = Color(0xfff9fafb))
                .padding(vertical = 12.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.Top
        ) {
            // DateTimeView
            Column(Modifier.weight(1.3f)) {
                Icon(Icons.Default.CalendarToday, contentDescription = null)
                Spacer(Modifier.height(2.dp))
                Text("DATE", color = textColorExtraLight, fontFamily = getFontFamily(weight = FontWeight.Bold), fontSize = 8.sp)
                Spacer(Modifier.height(2.dp))
                Text("Sat, 12 Jul", color = textColorDark, fontFamily = getFontFamily(weight = FontWeight.Bold), fontSize = 16.sp)
                Spacer(Modifier.height(2.dp))
                Text("2026", color = textColorLight, fontFamily = getFontFamily(), fontSize = 10.sp)
            }
            Box(
                Modifier
                    .fillMaxHeight()
                    .padding(end = 12.dp)
                    .width(1.dp)
                    .background(color = Color(0xffe5e7eb))
            )
            // DateTimeView
            Column(Modifier.weight(1f)) {
                Icon(Icons.Default.CalendarToday, contentDescription = null)
                Spacer(Modifier.height(2.dp))
                Text("PICKUP", color = textColorExtraLight, fontFamily = getFontFamily(weight = FontWeight.Bold), fontSize = 8.sp)
                Spacer(Modifier.height(2.dp))
                Text("3:00 PM", color = textColorDark, fontFamily = getFontFamily(weight = FontWeight.Bold), fontSize = 16.sp)
            }
            Box(
                Modifier
                    .fillMaxHeight()
                    .padding(end = 12.dp)
                    .width(1.dp)
                    .background(color = Color(0xffe5e7eb))
            )
            // DateTimeView
            Column(Modifier.weight(1.3f)) {
                Icon(Icons.Outlined.Alarm, contentDescription = null)
                Spacer(Modifier.height(2.dp))
                Text("DURATION", color = textColorExtraLight, fontFamily = getFontFamily(weight = FontWeight.Bold), fontSize = 8.sp)
                Spacer(Modifier.height(2.dp))
                Text("4-4.10 hrs", color = textColorDark, fontFamily = getFontFamily(weight = FontWeight.Bold), fontSize = 16.sp)
                Spacer(Modifier.height(2.dp))
                Text("Approx.", color = textColorLight, fontFamily = getFontFamily(), fontSize = 10.sp)
            }
        }
        Column(Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
            Text("Pickup Address", fontFamily = getFontFamily(weight = FontWeight.Bold), color = textColorExtraLight, fontSize = 8.sp)
            Spacer(Modifier.height(3.dp))
            Text("From Home (ATS Sarvana)", fontFamily = getFontFamily(weight = FontWeight.SemiBold), color = textColorDark, fontSize = 11.sp)
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(16.dp)
                    .border(width = 1.dp, color = Color(0xffe5e7eb), shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                    .background(color = Color(0xfff9f9f9), shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
            )
            Box(
                Modifier
                    .weight(1f)
                    .dottedLine(color = Color(0xffd1d5db))
            )
            Box(
                Modifier
                    .size(16.dp)
                    .border(width = 1.dp, color = Color(0xffe5e7eb), shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                    .background(color = Color(0xfff9f9f9), shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 20.dp)
        ) {
            Text("Projects You'll Visit (3)".uppercase(), fontFamily = getFontFamily(weight = FontWeight.Bold), color = textColorDark, fontSize = 8.sp)
            Text("Pavani Mirabilia Phase 2  → Brigade Utopia  → Purva Palm Beach", fontFamily = getFontFamily(), color = textColorDark, fontSize = 12.sp, lineHeight = 18.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(16.dp)
                    .border(width = 1.dp, color = Color(0xffe5e7eb), shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                    .background(color = Color(0xfff9f9f9), shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
            )
            Box(
                Modifier
                    .weight(1f)
                    .dottedLine(color = Color(0xffd1d5db))
            )
            Box(
                Modifier
                    .size(16.dp)
                    .border(width = 1.dp, color = Color(0xffe5e7eb), shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                    .background(color = Color(0xfff9f9f9), shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
            )
        }
    }
}

@Composable
fun ConfirmationFooter(modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(24.dp)
                    .background(color = Color.White, shape = CircleShape), contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.AccessAlarm, contentDescription = null, tint = textColorLight, modifier = Modifier.size(12.dp))
            }
            Spacer(Modifier.width(8.dp))
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(fontFamily = getFontFamily(weight = FontWeight.SemiBold))) {
                    append("Estimated visit duration (4-4.10hrs) \n")
                }
                append("may vary based on time spent at each project & traffic.")
            }, fontFamily = getFontFamily(), fontSize = 10.sp, color = textColorDark)
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(24.dp)
                    .background(color = Color.White, shape = CircleShape), contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.AccessAlarm, contentDescription = null, tint = textColorLight, modifier = Modifier.size(12.dp))
            }
            Spacer(Modifier.width(8.dp))
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(fontFamily = getFontFamily(weight = FontWeight.SemiBold))) {
                    append("Access your itinerary ")
                }
                append("any time from your dashboard.")
            }, fontFamily = getFontFamily(), fontSize = 10.sp, color = textColorDark)
        }
    }
}
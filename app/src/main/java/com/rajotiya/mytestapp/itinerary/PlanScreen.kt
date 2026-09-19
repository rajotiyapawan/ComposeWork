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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Brush
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
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.utility.DottedBorderBox
import com.rajotiya.mytestapp.utility.DottedLineOrientation
import com.rajotiya.mytestapp.utility.dottedLine
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 18-09-2026.
 */

@Preview
@Composable
private fun PlanScreenTest() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        PlanScreenHeader(Modifier.fillMaxWidth())
        SelectedProjectsRouteMap()
        Spacer(Modifier.weight(1f))
        Box(
            Modifier
                .fillMaxWidth()
                .dropShadow(shape = RectangleShape, shadow = Shadow(radius = 16.dp, color = Color(0x0f000000)))
                .border(width = 1.dp, color = Color(0xffeeeeee), shape = RectangleShape)
                .background(color = Color.White)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp)
        ) {
            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Icon(Icons.Default.AccessTime, contentDescription = null, modifier = Modifier.size(12.dp))
                    Spacer(Modifier.width(7.dp))
                    Text(buildAnnotatedString {
                        append("Pickup: ")
                        withStyle(style = SpanStyle(color = mbGreen, fontFamily = getFontFamily(weight = FontWeight.SemiBold))) {
                            append("3:00 PM")
                        }
                        append(" · Projects: ")
                        withStyle(style = SpanStyle(color = mbGreen, fontFamily = getFontFamily(weight = FontWeight.SemiBold))) {
                            append("3")
                        }
                        append(" ·  Est duration: ")
                        withStyle(style = SpanStyle(color = mbGreen, fontFamily = getFontFamily(weight = FontWeight.SemiBold))) {
                            append("~4-4.10 hrs")
                        }
                    }, fontFamily = getFontFamily(), fontSize = 10.sp, lineHeight = 16.sp, color = textColorLight)
                }
                Box(Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .background(color = mbRed, shape = RoundedCornerShape(50))
                    .padding(vertical = 8.dp), contentAlignment = Alignment.Center) {
                    Text("Continue", color = Color.White, fontSize = 14.sp, lineHeight = 20.sp, fontFamily = getFontFamily(weight = FontWeight.SemiBold))
                }
            }
        }
    }
}

@Composable
fun SelectedProjectsRouteMap(modifier: Modifier = Modifier) {
    Box(
        modifier
            .height(IntrinsicSize.Max)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xfffffafb), Color.White
                    )
                )
            )
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .padding(vertical = 10.dp, horizontal = 16.dp)
                .padding(start = 12.dp, bottom = 24.dp)
                .dottedLine(textColorDark, gapLength = 1.dp, dashLength = 2.dp, orientation = DottedLineOrientation.Vertical)
        )
        Column(Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .verticalScroll(state = rememberScrollState())
        ) {
            // PickUp
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(24.dp).background(color = Color.White, shape = CircleShape)) {
                    Image(painter = painterResource(R.drawable.home_circular_icon_grey), contentDescription = null)
                }
                Spacer(Modifier.width(7.dp))
                Text(
                    buildAnnotatedString {
                        append("Your pickup from ")
                        withStyle(style = SpanStyle(color = textColorDark)) {
                            append("Home")
                        }
                        append(" (ATS One hamlet) - ")
                        withStyle(style = SpanStyle(color = textColorDark)) {
                            append("11:00 AM")
                        }
                    },
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 10.sp,
                    lineHeight = 17.3.sp,
                    color = textColorExtraLight
                )
            }

            Spacer(Modifier.height(16.dp))

            TimeDistanceBetweenView(Modifier
                .fillMaxWidth()
                .padding(start = 6.dp))

            Spacer(Modifier.height(10.dp))
            PlannedProjectItem()

            Spacer(Modifier.height(16.dp))

            TimeDistanceBetweenView(Modifier
                .fillMaxWidth()
                .padding(start = 6.dp))

            Spacer(Modifier.height(10.dp))
            PlannedProjectItem()

            Spacer(Modifier.height(16.dp))

            TimeDistanceBetweenView(Modifier
                .fillMaxWidth()
                .padding(start = 6.dp))

            Spacer(Modifier.height(10.dp))
            PlannedProjectItem()

            Spacer(Modifier.height(26.dp))

            UnplannedProjectItem()

            Spacer(Modifier.height(16.dp))

            TimeDistanceBetweenView(Modifier
                .fillMaxWidth()
                .padding(start = 6.dp))

            Spacer(Modifier.height(10.dp))
            // DropOff
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier
                    .size(24.dp)
                    .background(color = Color.White, shape = CircleShape)) {
                    Image(painter = painterResource(R.drawable.home_circular_icon_grey), contentDescription = null)
                }
                Spacer(Modifier.width(7.dp))
                Text(
                    buildAnnotatedString {
                        append("Drop-off at ")
                        withStyle(style = SpanStyle(color = textColorDark)) {
                            append("Home")
                        }
                    },
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 10.sp,
                    lineHeight = 17.3.sp,
                    color = textColorExtraLight
                )
            }
        }
    }
}

@Composable
private fun TimeDistanceBetweenView(modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.ArrowDownward, contentDescription = null, modifier = Modifier.size(12.dp))
        Spacer(Modifier.width(7.dp))
        Text(
            "14.5 km  ·  ~ 30 min drive",
            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
            fontSize = 10.sp,
            lineHeight = 17.3.sp,
            color = textColorExtraLight
        )
    }
}

@Composable
fun UnplannedProjectItem(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        DottedBorderBox(
            Modifier
                .size(24.dp)
                .background(color = Color.White, shape = CircleShape),
            borderColor = Color(0xffd1d1d6), borderWidth = 2.dp,
            cornerRadius = 24.dp
        ) {
            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier
                .size(14.dp)
                .align(alignment = Alignment.Center), tint = Color(0xff8e8e93))
        }
        Spacer(Modifier.width(7.dp))
        DottedBorderBox(
            cornerRadius = 12.dp,
            borderWidth = 2.dp,
            borderColor = Color(0xffd8d8e4),
            content = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .size(36.dp)
                            .background(color = Color(0xfffff0f0), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(14.dp), tint = mbRed)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Add Another Project",
                            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = textColorDark
                        )
                        Text(
                            "Maximum 4 projects per itinerary",
                            fontFamily = getFontFamily(weight = FontWeight.Medium),
                            fontSize = 10.sp,
                            lineHeight = 15.sp,
                            color = textColorExtraLight
                        )
                    }
                }
            }
        )
    }
}


@Composable
fun PlannedProjectItem(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(24.dp)
                .border(width = 1.dp, color = mbGreen, shape = CircleShape)
                .background(color = Color(0xffedfaf9), shape = CircleShape), contentAlignment = Alignment.Center
        ) {
            Text(
                "1",
                fontFamily = getFontFamily(weight = FontWeight.Bold),
                fontSize = 10.sp,
                lineHeight = 15.sp,
                color = textColorDark
            )
        }
        Spacer(Modifier.width(7.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color(0xfff5f5f5), shape = RoundedCornerShape(12.dp))
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(56.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(6.dp))
            ) {}
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    "Pavani Mirabilia Phase 2",
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = textColorDark
                )
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        "₹1.95 Cr",
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                        fontSize = 12.sp,
                        lineHeight = 15.8.sp,
                        color = textColorDark
                    )
                    Text(
                        " •  3 BHK",
                        fontFamily = getFontFamily(weight = FontWeight.Medium),
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        color = textColorExtraLight
                    )
                    Text(
                        "  •  1,496 Sqft",
                        fontFamily = getFontFamily(weight = FontWeight.Medium),
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        color = textColorExtraLight
                    )
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    "Buyers typically spend ~45 mins",
                    fontFamily = getFontFamily(weight = FontWeight.Medium),
                    fontSize = 8.sp,
                    lineHeight = 12.sp,
                    color = textColorLight,
                    modifier = Modifier
                        .background(Color(0xfff5f5f5), shape = RoundedCornerShape(50))
                        .padding(horizontal = 8.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Box(
                Modifier
                    .size(20.dp)
                    .background(color = Color(0xfff5f5f5), shape = CircleShape), contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = textColorExtraLight, modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun PlanScreenHeader(modifier: Modifier = Modifier) {
    Row(
        modifier
            .background(color = Color.White)
            .padding(vertical = 12.dp)
    ) {
        Box(Modifier.noRippleClick {}) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
        }
        Spacer(Modifier.width(8.dp))
        Column {
            Text(
                "Your Site Visit Plan",
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                fontSize = 16.sp,
                color = textColorDark
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Sat, 12 Jul · Starting 11:00 AM",
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                fontSize = 12.sp,
                lineHeight = 16.5.sp,
                color = textColorLight
            )
        }
    }
}
package com.rajotiya.mytestapp.itinerary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.rajotiya.mytestapp.utility.DottedLineOrientation
import com.rajotiya.mytestapp.utility.dottedLine
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

@Preview
@Composable
private fun ConflictScreenTest() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ConflictScreenHeader(Modifier.fillMaxWidth())
        ConflictScreenBody(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 16.dp)
        )
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
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .background(color = mbRed, shape = RoundedCornerShape(50))
                        .padding(vertical = 8.dp), contentAlignment = Alignment.Center
                ) {
                    Text("Continue", color = Color.White, fontSize = 14.sp, lineHeight = 20.sp, fontFamily = getFontFamily(weight = FontWeight.SemiBold))
                }
            }
        }
    }
}

@Composable
fun ConflictScreenHeader(modifier: Modifier = Modifier) {
    Row(modifier.padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Column {
            Text("Your Site Visit Plan", fontFamily = getFontFamily(weight = FontWeight.SemiBold), fontSize = 16.sp)
            Spacer(Modifier.width(6.dp))
            Text("Sat, 12 Jul · Starting 11:00 AM", fontFamily = getFontFamily(weight = FontWeight.SemiBold), fontSize = 12.sp, lineHeight = 16.5.sp, color = textColorLight)
        }
    }
    Box(
        Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = Color(0xffeeeeee))
    )
}

data class ConflictTab(val title: String, val icon: ImageVector)

@Composable
fun ConflictScreenBody(modifier: Modifier = Modifier) {
    Column(modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color(0xffffebf0), shape = RoundedCornerShape(8.dp))
                .background(color = Color(0xfffffafb), shape = RoundedCornerShape(8.dp))
                .padding(vertical = 10.dp, horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.Info, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color(0xfff197a2))
            Spacer(Modifier.width(7.dp))
            Text("Project visits end at 6:00 PM.\n Earlier pickups lets one cover more projects.", fontFamily = getFontFamily(), fontSize = 12.sp, color = textColorDark)
        }
        Spacer(Modifier.height(30.dp))
        Text("With a 3:00 PM pickup, you can visit only 2/4 selected projects", fontFamily = getFontFamily(weight = FontWeight.SemiBold), fontSize = 16.sp, lineHeight = 24.sp, color = textColorDark)
        Spacer(Modifier.height(14.dp))
        Text("Choose how would you like to continue?", fontFamily = getFontFamily(), fontSize = 12.sp, lineHeight = 28.sp, color = textColorDark)
        Spacer(Modifier.height(4.dp))
        var tabSelected by remember { mutableStateOf(1) }
        val tabs = listOf(
            ConflictTab("Update Pickup Time", Icons.Default.Repeat),
            ConflictTab("Use Current Pickup", Icons.Default.AccessTime)
        )
        Row(Modifier.fillMaxWidth()) {
            tabs.forEachIndexed { index, tab ->
                Box(
                    Modifier
                        .weight(1f)
                        .border(width = 1.dp, color = if (tabSelected == index) mbGreen else Color.Transparent, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        .background(color = if (tabSelected == index) Color(0xffedfaf9) else Color.Transparent)
                        .padding(vertical = 4.dp, horizontal = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(tab.icon, contentDescription = null, modifier = Modifier.size(12.dp), if (tabSelected == 0) mbGreen else textColorExtraLight)
                        Spacer(Modifier.width(6.dp))
                        Text(
                            tab.title,
                            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                            fontSize = 12.sp,
                            lineHeight = 28.sp,
                            color = if (tabSelected == index) mbGreen else textColorExtraLight
                        )
                    }
                }
            }
        }
        if (tabSelected == 0) {
            UpdatePickupTime(Modifier.fillMaxWidth())
        } else {
            CurrentPickupTime(Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun UpdatePickupTime(modifier: Modifier = Modifier) {
    val list = listOf("9:00 am", "9:30 am", "10:00 am", "10:30 am", "11:00 am")
    var selectedTime by remember { mutableStateOf("9:00 am") }
    Box(
        modifier
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
            .border(width = 1.dp, color = Color(0xffe0f2f0), shape = RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
            .background(brush = Brush.verticalGradient(colors = listOf(Color(0xffedfaf9), Color.White)), shape = RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
        ) {
            LazyRow(Modifier.fillMaxWidth(), contentPadding = PaddingValues(end = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(list) {
                    Box(
                        Modifier
                            .noRippleClick { selectedTime = it }
                            .border(width = 1.dp, color = if (it == selectedTime) mbGreen else Color(0xffd7d7d7), shape = RoundedCornerShape(50))
                            .background(color = if (it == selectedTime) Color(0xffedfaf9) else Color.White, shape = RoundedCornerShape(50))
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text(
                            it.uppercase(),
                            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                            fontSize = 14.sp,
                            lineHeight = 26.sp,
                            color = if (it == selectedTime) mbGreen else textColorLight,
                        )
                    }
                }
            }
            Spacer(Modifier.height(11.dp))
            Text("Current pickup time 3:00 PM", color = textColorExtraLight, fontFamily = getFontFamily(), fontSize = 10.sp)
        }
    }
}

@Composable
fun CurrentPickupTime(modifier: Modifier = Modifier) {
    Box(
        modifier
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
            .border(width = 1.dp, color = Color(0xffe0f2f0), shape = RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
            .background(brush = Brush.verticalGradient(colors = listOf(Color(0xffedfaf9), Color.White)), shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
            .padding(16.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                "Remove 1–2 projects from your list to finish by 6 PM",
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                fontSize = 14.sp,
                lineHeight = 28.sp,
                color = textColorDark
            )
            Spacer(Modifier.height(20.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
            ) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .padding(start = 12.dp)
                        .dottedLine(textColorDark, gapLength = 1.dp, dashLength = 2.dp, orientation = DottedLineOrientation.Vertical)
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(state = rememberScrollState())
                ) {
                    ConflictPlannedProjectItem(defaultProject = true)

                    Spacer(Modifier.height(7.dp))

                    TimeDistanceBetweenView(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp)
                    )
                    Spacer(Modifier.height(7.dp))
                    ConflictPlannedProjectItem()

                    Spacer(Modifier.height(7.dp))

                    TimeDistanceBetweenView(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp)
                    )
                    Spacer(Modifier.height(7.dp))
                    ConflictPlannedProjectItem()

                    Spacer(Modifier.height(7.dp))

                    TimeDistanceBetweenView(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp)
                    )
                    Spacer(Modifier.height(7.dp))
                    ConflictPlannedProjectItem()

                    Spacer(Modifier.height(7.dp))

                    TimeDistanceBetweenView(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ConflictPlannedProjectItem(modifier: Modifier = Modifier, defaultProject: Boolean = false) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(24.dp)
                .background(color = if (defaultProject) Color(0xfff5f5f5) else Color(0xffedfaf9), shape = CircleShape), contentAlignment = Alignment.Center
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
                .border(width = 1.dp, color = if (defaultProject) Color(0xfff5f5f5) else Color(0xffe8e8e8), shape = RoundedCornerShape(12.dp))
                .background(color = if (defaultProject) Color(0xfff5f5f5) else Color.White, shape = RoundedCornerShape(12.dp))
                .padding(start = 12.dp, top = 8.dp, bottom = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    buildAnnotatedString {
                        append("Pavani Mirabilia Phase 2,")
                        withStyle(style = SpanStyle(fontFamily = getFontFamily())) {
                            append(" Whitefield")
                        }
                    },
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = textColorDark
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_green_tick_009681_icon), contentDescription = null, modifier = Modifier
                            .size(10.dp)
                            .padding(2.dp)
                    )
                    Text(
                        "₹1.95 Cr",
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                        fontSize = 8.sp,
                        lineHeight = 10.sp,
                        color = textColorLight
                    )
                    Spacer(Modifier.width(4.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_green_tick_009681_icon), contentDescription = null, modifier = Modifier
                            .size(10.dp)
                            .padding(2.dp)
                    )
                    Text(
                        "Under ₹1.2Cr",
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                        fontSize = 8.sp,
                        lineHeight = 10.sp,
                        color = textColorLight
                    )
                    Spacer(Modifier.width(4.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_green_tick_009681_icon), contentDescription = null, modifier = Modifier
                            .size(10.dp)
                            .padding(2.dp)
                    )
                    Text(
                        "Ready to move",
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                        fontSize = 8.sp,
                        lineHeight = 10.sp,
                        color = textColorLight
                    )
                }
                Spacer(Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccessTime, contentDescription = null, tint = textColorExtraLight, modifier = Modifier.size(10.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "Buyers typically spend ~45 mins",
                        fontFamily = getFontFamily(weight = FontWeight.Medium),
                        fontSize = 8.sp,
                        lineHeight = 12.sp,
                        color = textColorLight,
                    )
                }
            }
            if (!defaultProject) {
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
}
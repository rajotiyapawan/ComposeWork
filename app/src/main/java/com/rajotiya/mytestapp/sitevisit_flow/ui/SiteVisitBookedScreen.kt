package com.rajotiya.mytestapp.sitevisit_flow.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.utility.BottomPopupDialog
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

/**
 * Created by Pawan Rajotiya on 06-03-2025.
 */

@Composable
fun SiteVisitBooked(modifier: Modifier = Modifier) {
    val showDetailPopUp = remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        LazyColumn(modifier = modifier) {
            item {
                BookingDetails(modifier = Modifier.fillMaxWidth(), showDetailPopUp)
            }
            item {
                CabDetails(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                )
            }
            item {
                TrackYourBooking(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }
        BottomButton(
            Modifier
                .fillMaxWidth()
                .height(64.dp)
                .align(Alignment.BottomCenter)
        ) {}
    }

    BottomPopupDialog(
        showDialog = showDetailPopUp.value,
        onDismiss = { showDetailPopUp.value = false }
    ) {
        SVBookingDetailsDialog(Modifier.fillMaxWidth())
    }
}

@Composable
private fun BookingDetails(modifier: Modifier = Modifier, showDetailPopUp: MutableState<Boolean>) {
    Column(modifier = modifier.padding(top = 60.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(R.drawable.ic_tick_green), contentDescription = null)
        Spacer(Modifier.height(8.dp))
        Text(
            "Site Visit booked successfully!",
            fontSize = 18.sp,
            lineHeight = 21.sp,
            color = textColorDark,
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Tue 9 Feb  | 9:00 AM",
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = textColorDark,
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
            )
            Row(Modifier.noRippleClick { showDetailPopUp.value = true }, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "View Booking Details",
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = textColorDark,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
                )
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun CabDetails(modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(horizontal = 16.dp), contentAlignment = Alignment.TopStart) {
        Card(
            modifier = Modifier.padding(top = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(width = 1.dp, color = Color(0xffd7d7d7)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "KAXX XXXX18",
                            color = textColorDark,
                            fontSize = 14.sp,
                            lineHeight = 19.sp,
                            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                        )
                        Text(
                            "White ETIOS CNG",
                            color = textColorExtraLight,
                            fontSize = 12.sp,
                            lineHeight = 19.sp,
                            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "Deepak Singh",
                                color = textColorExtraLight,
                                fontSize = 12.sp,
                                lineHeight = 19.sp,
                                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
                            )
                            Spacer(Modifier.width(4.dp))
                            Icon(
                                Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                                tint = Color(0xffFFC72C)
                            )
                            Spacer(Modifier.width(2.dp))
                            Text(
                                "4.8",
                                color = textColorExtraLight,
                                fontSize = 12.sp,
                                lineHeight = 19.sp,
                                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
                            )
                        }
                    }
                    Image(painter = painterResource(R.drawable.site_visit_cab), contentDescription = null)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Things to Remember",
                        fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = textColorDark
                    )
                    Spacer(Modifier.width(12.dp))
                    Box(
                        Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(color = Color(0xffb2dfd8))
                    )
                }
                repeat(3) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Check,
                            tint = Color(0xff009681),
                            contentDescription = null,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            "It's absolutely Free - no hidden charges!",
                            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            color = textColorDark
                        )

                    }
                }
                Spacer(Modifier.height(24.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xffFFF7E1), shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp))
                        .padding(12.dp)
                ) {
                    Icon(Icons.Filled.ThumbUp, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "We will call you 30 mins before pickup to confirm your exact location",
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = textColorDark
                    )
                }
            }
        }
        Box(
            Modifier
                .padding(start = 16.dp)
                .background(color = Color(0xffDFF6F9), shape = RoundedCornerShape(4.dp))
        ) {
            Text(
                "Cab Details",
                color = Color.Black,
                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                fontSize = 10.sp,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun TrackYourBooking(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            "Track your Bookings".uppercase(),
            fontSize = 12.sp,
            lineHeight = 20.sp,
            color = textColorDark,
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .background(color = Color(0xffF2FBFC), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Text(
                "You can track & edit your site visit bookings only on the App!",
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = textColorDark,
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
            )
        }
    }
}

@Composable
private fun BottomButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(brush = Brush.verticalGradient(colors = listOf(Color.Transparent, Color(0x33303030))))
        )
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.Center) {
            Box(
                Modifier
                    .fillMaxWidth(0.5f)
                    .background(color = mbRed, shape = RoundedCornerShape(50))
                    .noRippleClick { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Continue",
                    color = Color.White,
                    fontSize = 14.sp,
                    lineHeight = 32.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                )
            }
        }
    }
}
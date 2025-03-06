package com.rajotiya.mytestapp.sitevisit_flow.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 06-03-2025.
 */

@Composable
fun SVBookingDetailsDialog(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Booking Details",
            fontSize = 18.sp,
            lineHeight = 24.sp,
            color = textColorDark,
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
        )
        ProjectDetails(
            modifier = Modifier
                .fillMaxWidth()
        )
        BookingDetailItem(modifier = Modifier.fillMaxWidth())
        BookingDetailItem(modifier = Modifier.fillMaxWidth())
    }
}


@Composable
private fun BookingDetailItem(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.Top) {
        Icon(Icons.Default.Home, contentDescription = null)
        Spacer(Modifier.width(9.dp))
        Column {
            Text(
                "Date & Time",
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = textColorLight,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
            Text(
                "Tue 9 Feb  | 9:00 AM",
                fontSize = 12.sp,
                lineHeight = 20.sp,
                color = textColorDark,
                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
            )
        }
    }
}

@Composable
private fun ProjectDetails(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.Top) {
        Icon(Icons.Default.Home, contentDescription = null)
        Spacer(Modifier.width(9.dp))
        Column {
            Text(
                "Project",
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = textColorLight,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                repeat(2) {
                    ProjectItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .border(width = 1.dp, color = Color(0xffe8e8e8), shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            "Pavani Mirabilia, Whitefield, Bangalore",
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = textColorDark,
            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
        )
        Text(
            "₹1.75 Cr   |   3BHK   |   1726 sqft",
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = textColorExtraLight,
            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
        )
        Text(
            "Possession by Dec’25",
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = textColorExtraLight,
            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
        )
    }
}
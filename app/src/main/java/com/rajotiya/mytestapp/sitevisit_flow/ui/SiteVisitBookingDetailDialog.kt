package com.rajotiya.mytestapp.sitevisit_flow.ui

import androidx.compose.foundation.background
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
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.sitevisit_flow.domain.models.SiteVisitFlowData
import com.rajotiya.mytestapp.sitevisit_flow.domain.models.SvSavedResponse
import com.rajotiya.mytestapp.sitevisit_flow.ui.common_views.SvCommonProjectItemView
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 06-03-2025.
 */

@Composable
fun SVBookingDetailsDialog(modifier: Modifier = Modifier, bookingDetails: SvSavedResponse.SvBookingDetails) {
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
                .fillMaxWidth(), projects = bookingDetails.projects
        )
        BookingDetailDateTimeItem(modifier = Modifier.fillMaxWidth(), bookingDetails.date, bookingDetails.time)
        BookingDetailPickUpItem(modifier = Modifier.fillMaxWidth(), bookingDetails.pickUpLocation)
    }
}


@Composable
private fun BookingDetailDateTimeItem(modifier: Modifier = Modifier, date: String, time: String) {
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
                "$date  | $time",
                fontSize = 12.sp,
                lineHeight = 20.sp,
                color = textColorDark,
                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
            )
        }
    }
}

@Composable
private fun BookingDetailPickUpItem(modifier: Modifier = Modifier, pickUpLocation: String) {
    Row(modifier = modifier, verticalAlignment = Alignment.Top) {
        Icon(Icons.Default.Home, contentDescription = null)
        Spacer(Modifier.width(9.dp))
        Column {
            Text(
                "Pickup Location",
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = textColorLight,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
            Text(
                "$pickUpLocation",
                fontSize = 12.sp,
                lineHeight = 20.sp,
                color = textColorDark,
                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
            )
        }
    }
}

@Composable
private fun ProjectDetails(modifier: Modifier = Modifier, projects: List<SiteVisitFlowData.SvProjectItem>) {
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
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                projects.forEach { projectItem ->
                    SvCommonProjectItemView(
                        modifier = Modifier
                            .fillMaxWidth(), projectItem, backgroundColor = Color(0xfff5f5f5)
                    )
                }
            }
        }
    }
}
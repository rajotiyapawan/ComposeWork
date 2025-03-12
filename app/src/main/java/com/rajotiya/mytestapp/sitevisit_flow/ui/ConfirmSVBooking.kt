package com.rajotiya.mytestapp.sitevisit_flow.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitFlowActivity
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitFlowViewModel
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitScreens
import com.rajotiya.mytestapp.sitevisit_flow.SvFlowUserEvents
import com.rajotiya.mytestapp.sitevisit_flow.domain.models.SiteVisitFlowData
import com.rajotiya.mytestapp.sitevisit_flow.ui.common_views.SvCommonTopBar
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.LoaderUI
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

@Composable
fun ConfirmSVBooking(modifier: Modifier = Modifier) {
    val viewModel: SiteVisitFlowViewModel = SiteVisitFlowActivity.LocalSiteVisitFlowViewModel.current
    InflateConfirmBookingUI(modifier = modifier, viewModel = viewModel)
    HandleSaveSvDetails(modifier = modifier, viewModel = viewModel)
}

@Composable
private fun HandleSaveSvDetails(modifier: Modifier = Modifier, viewModel: SiteVisitFlowViewModel) {
    val svSavedResponse by viewModel.saveSvBooking.collectAsState()
    if (!svSavedResponse.isIdle) {
        when (val response = svSavedResponse.apiState) {
            is MBCoreResultEvent.OnFailure -> {}
            MBCoreResultEvent.OnLoading -> {
                LoaderUI(modifier)
            }

            is MBCoreResultEvent.OnSuccess -> {
                LoaderUI(modifier)
                viewModel.sendUserEvent(
                    SvFlowUserEvents.NavigateTo(
                        route = SiteVisitScreens.SiteVisitBooked.name,
                        currentScreen = SiteVisitScreens.ConfirmBooking.name,
                        saveToBackStack = false
                    )
                )
            }

            null -> {}
        }
    }
}

@Composable
private fun InflateConfirmBookingUI(
    modifier: Modifier = Modifier,
    viewModel: SiteVisitFlowViewModel
) {
    val svFlowState by viewModel.svFlowData.collectAsState()
    Box(modifier = modifier) {
        Column(modifier = modifier.background(color = Color.White)) {
            SvCommonTopBar(Modifier.fillMaxWidth()) { viewModel.sendUserEvent(SvFlowUserEvents.SkipBtnClicked) }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 20.dp, end = 17.dp)
            ) {
                Text(
                    "Confirm Your Booking",
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    color = textColorDark,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                )
                DateTimeView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    date = svFlowState.date ?: "",
                    time = svFlowState.time ?: "",
                    onEdit = { viewModel.sendUserEvent(SvFlowUserEvents.NavigateTo(route = SiteVisitScreens.FreeCabIntro.name)) })
                svFlowState.projects?.let {
                    ProjectsSelectedView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        projects = it
                    )
                }
                PickUpLocationView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    pickUpLocation = svFlowState.pickUpLocation ?: "",
                    onEdit = {
                        viewModel.sendUserEvent(SvFlowUserEvents.PopBackTo(route = SiteVisitScreens.SVPickUpLocation.name))
                    })
            }
        }
        BottomButton(
            Modifier
                .fillMaxWidth()
                .height(64.dp)
                .align(Alignment.BottomCenter)
        ) {
            viewModel.saveSvBooking()
        }
    }
}

@Composable
private fun DateTimeView(modifier: Modifier = Modifier, date: String, time: String, onEdit: () -> Unit) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Outlined.DateRange, contentDescription = null)
        Spacer(Modifier.width(7.dp))
        Column {
            Text(
                "Date & Time",
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = textColorLight,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
            Row {
                Text(
                    "$date  | $time",
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    color = textColorDark,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
                )
                Spacer(Modifier.width(7.dp))
                Box(Modifier.noRippleClick { onEdit() }) {
                    Text(
                        "Edit",
                        fontSize = 12.sp,
                        lineHeight = 19.sp,
                        color = mbRed,
                        textDecoration = TextDecoration.Underline,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectsSelectedView(modifier: Modifier = Modifier, projects: List<SiteVisitFlowData.SvProjectItem>) {
    Row(modifier = modifier) {
        Icon(Icons.Outlined.DateRange, contentDescription = null)
        Spacer(Modifier.width(7.dp))
        Column {
            Text(
                "Project",
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = textColorLight,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                projects.forEach { projectItem ->
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .border(width = 1.dp, color = Color(0xffe8e8e8), shape = RoundedCornerShape(6.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            "${projectItem.prjName}, ${projectItem.prjCity}",
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = textColorDark,
                            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
                        )
                        Text(
                            "₹${projectItem.price}   |   ${projectItem.propType}   |   ${projectItem.area}",
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = textColorExtraLight,
                            fontFamily =
                            getFontFamily
                                (
                                Constants
                                    .MONTSERRAT_MEDIUM
                            )
                        )
                        Text(
                            "Possession by ${projectItem.possessionBy}",
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = textColorExtraLight,
                            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PickUpLocationView(modifier: Modifier = Modifier, pickUpLocation: String, onEdit: () -> Unit) {
    Row(modifier = modifier) {
        Icon(Icons.Outlined.DateRange, contentDescription = null)
        Spacer(Modifier.width(7.dp))
        Column {
            Text(
                "Pickup Location",
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = textColorLight,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
            Row {
                Text(
                    pickUpLocation,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    color = textColorDark,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
                )
                Spacer(Modifier.width(6.dp))
                Box(Modifier.noRippleClick { onEdit() }) {
                    Text(
                        "Edit",
                        fontSize = 12.sp,
                        lineHeight = 19.sp,
                        color = mbRed,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
            Box(
                Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
                    .height(142.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(width = 1.dp, color = Color(0xffd7d7d7), shape = RoundedCornerShape(10.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.loyalty_landing_bg),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
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
                .weight(1f), contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.5f)
                    .background(color = mbRed, shape = RoundedCornerShape(50))
                    .noRippleClick { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Confirm Booking",
                    color = Color.White,
                    fontSize = 14.sp,
                    lineHeight = 32.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                )
            }
        }
    }
}
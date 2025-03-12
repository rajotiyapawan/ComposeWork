package com.rajotiya.mytestapp.sitevisit_flow.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitFlowActivity
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitFlowViewModel
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitScreens
import com.rajotiya.mytestapp.sitevisit_flow.SvFlowUserEvents
import com.rajotiya.mytestapp.sitevisit_flow.ui.common_views.SvCommonTopBar
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

@Composable
fun SVPickUpLocationScreen(modifier: Modifier = Modifier) {
    val viewModel = SiteVisitFlowActivity.LocalSiteVisitFlowViewModel.current
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val localFocusManager = LocalFocusManager.current
    Column(modifier = modifier
        .noRippleClick {
            localFocusManager.clearFocus()
        }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(bottom = 12.dp)
        ) {
            SvCommonTopBar(
                modifier = Modifier.fillMaxWidth(),
                onBack = { viewModel.sendUserEvent(SvFlowUserEvents.SkipBtnClicked) })
            Spacer(Modifier.height(12.dp))
            SvPickUpLocationHeading(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
                onEdit = {
                    viewModel.sendUserEvent(SvFlowUserEvents.PopBackTo(route = SiteVisitScreens.FreeCabIntro.name))
                })
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(R.drawable.sv_pickup_location_bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                SvSearchBox(modifier = Modifier.fillMaxWidth(), textFieldValue, onValueChange = {
                    textFieldValue = it
                    viewModel.getLocationSearchResults(it.text)
                })
                if (textFieldValue.text.isEmpty()) {
                    SvPickUpLocationNote(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 17.dp)
                    )
                }
                HandleSearchResults(modifier = Modifier.fillMaxWidth(), viewModel = viewModel) {
                    textFieldValue = TextFieldValue(text = it, selection = TextRange(it.length))
                    viewModel.savePickUpLocation(it)
//                    viewModel.sendUserEvent(SvFlowUserEvents.NavigateTo(route = SiteVisitScreens.ConfirmBooking.name))
                }
            }
        }
    }
}

@Composable
private fun SvPickUpLocationHeading(modifier: Modifier = Modifier, onEdit: () -> Unit) {
    Column(
        modifier
            .padding(horizontal = 20.dp)
    ) {
        Text(
            "Enter Pickup Location",
            color = textColorDark,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
        )
        Spacer(Modifier.height(3.dp))
        Row {
            Text(
                "Sun 9 Feb  | 9:00 AM",
                color = textColorLight,
                fontSize = 14.sp,
                lineHeight = 19.sp,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
            Spacer(Modifier.width(6.dp))
            Box(Modifier.noRippleClick { onEdit() }) {
                Text(
                    "Edit",
                    modifier = Modifier.noRippleClick { },
                    color = mbRed,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 14.sp,
                    lineHeight = 19.sp,
                    fontFamily = getFontFamily(
                        Constants
                            .MONTSERRAT_REGULAR
                    )
                )
            }
        }
    }
}

@Composable
private fun SvSearchBox(modifier: Modifier = Modifier, textValue: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = textValue,
        onValueChange,
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
            color = textColorLight
        ),
        modifier = modifier,
        placeholder = {
            Text(
                "Enter Locality/Project/Landmark",
                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                fontSize = 14.sp,
                color = textColorLight
            )
        },
        trailingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
            focusedBorderColor = Color(0xff909090), unfocusedBorderColor = Color(0xffd7d7d7)
        )
    )
}

@Composable
private fun SvPickUpLocationNote(modifier: Modifier = Modifier) {
    Row(
        modifier
            .background(color = Color(0xffFFF7E1), shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Outlined.Info, contentDescription = null, modifier = Modifier.size(10.dp))
        Spacer(Modifier.width(4.dp))
        Text(
            "Share your exact location at the time of pickup",
            fontSize = 12.sp,
            lineHeight = 24.sp,
            color = textColorDark,
            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
        )
    }
}

@Composable
private fun HandleSearchResults(
    modifier: Modifier = Modifier,
    viewModel: SiteVisitFlowViewModel,
    onSearchItemSelected: (item: String) -> Unit
) {
    val searchResults by viewModel.svLocationSearch.collectAsState()
    if (!searchResults.isIdle) {
        when (val response = searchResults.apiState) {
            is MBCoreResultEvent.OnFailure -> {}
            MBCoreResultEvent.OnLoading -> {}
            is MBCoreResultEvent.OnSuccess -> {
                SearchResultsView(modifier = modifier, data = response.data, onSearchItemSelected = onSearchItemSelected)
            }

            null -> {}
        }
    }
}

@Composable
private fun SearchResultsView(modifier: Modifier = Modifier, data: List<String>, onSearchItemSelected: (item: String) -> Unit) {
    LazyColumn(
        modifier = modifier
            .heightIn(max = 200.dp)
            .border(width = 1.dp, color = Color(0xffd7d7d7), shape = RoundedCornerShape(8.dp))
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(start = 16.dp, end = 11.dp),
        contentPadding = PaddingValues(bottom = 10.dp)
    ) {
        itemsIndexed(data) { index, item ->
            Column(Modifier
                .fillMaxWidth()
                .noRippleClick { onSearchItemSelected(item) }
                .padding(top = 16.dp, bottom = 15.dp)) {
                Text(item, fontSize = 14.sp, color = textColorLight, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR))
            }
            if (index < data.size - 1) {
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(0xffdcdcdc))
            }
        }
    }
}
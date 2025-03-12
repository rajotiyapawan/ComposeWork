package com.rajotiya.mytestapp.sitevisit_flow.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitFlowActivity
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitFlowViewModel
import com.rajotiya.mytestapp.sitevisit_flow.SvFlowUserEvents
import com.rajotiya.mytestapp.sitevisit_flow.TimeSlot
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Pawan Rajotiya on 12-03-2025.
 */

@Composable
fun SiteVisitTimeSlotSelectionScreen(modifier: Modifier = Modifier) {
    val viewModel = SiteVisitFlowActivity.LocalSiteVisitFlowViewModel.current
    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Box(Modifier.noRippleClick { viewModel.sendUserEvent(SvFlowUserEvents.SkipBtnClicked) }) {
                Text(
                    text = "Skip",
                    color = textColorLight,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_MEDIUM))
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = buildAnnotatedString {
                append("Schedule your visit with ")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xff009681),
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                    )
                ) {
                    append("Free")
                }
                append(" Cab")
            },
            fontSize = 18.sp,
            lineHeight = 22.sp,
            color = textColorDark,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "SELECT DATE",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                fontSize = 12.sp,
                color = textColorDark
            )
            Spacer(modifier = Modifier.width(4.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xffe8e8e8)
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        DateSelectionGrid(viewModel)
        if (viewModel.selectedDate == null) {
            Spacer(modifier = Modifier.weight(1f))
            WhatYouGetSection()
        } else {
            Spacer(modifier = Modifier.height(26.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "SELECT Time",
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                    fontSize = 12.sp,
                    color = textColorDark
                )
                Spacer(modifier = Modifier.width(4.dp))
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color(0xffe8e8e8)
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            TimeSlotScreen(viewModel)
        }
    }
}

@Composable
fun DateSelectionGrid(viewModel: SiteVisitFlowViewModel) {
    val dates = listOf(
        "TODAY" to "8 Feb", "TUE" to "9 Feb", "WED" to "10 Feb", "THU" to "11 Feb",
        "FRI" to "12 Feb", "SAT" to "13 Feb", "SUN" to "14 Feb", "" to "Next \nWeek"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dates.size) { index ->
            val (day, date) = dates[index]
            DateItem(item = dates[index], selected = viewModel.selectedDate == index) {
                viewModel.selectedDate = index
            }
//            if (viewModel.selectedDate == index)
//                DateBoxChecked(day = day, date = date)
//            else
//                DateBoxUnchecked(day, date, onClick = { viewModel.selectedDate = index })
        }
    }
}

@Composable
private fun DateItem(modifier: Modifier = Modifier, item: Pair<String, String>, selected: Boolean, onClick: () -> Unit) {
    val (day, dateMonth) = item
    val dateMonths = dateMonth.split(" ")
    val date = dateMonths[0]
    val month = dateMonths[1]
    Box(
        modifier = Modifier
            .padding(top = 4.dp, end = 4.dp)
            .height(82.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .border(
                    1.dp,
                    if (selected) Color(0xffb2dfd8) else Color(0xffd7d7d7),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(if (selected) Color(0xffedfaf9) else Color.White, shape = RoundedCornerShape(8.dp))
                .noRippleClick { onClick() }
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(if (selected) Color(0xff009681) else Color(0xffd7d7d7))
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                    fontSize = 12.sp, lineHeight = 16.sp,
                    color = if (selected) Color.White else textColorDark,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            if (!day.equals("")) {
                Text(
                    text = date,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                    fontSize = 24.sp, lineHeight = 28.sp,
                    color = textColorDark,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = month,
                    fontSize = 12.sp, lineHeight = 16.sp,
                    color = textColorDark,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT)),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            } else {
                Text(
                    text = date,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                    fontSize = 12.sp, lineHeight = 16.sp,
                    color = textColorDark,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
        if (selected) {
            Image(
                painter = painterResource(id = R.drawable.ic_tick_green),
                contentDescription = null,
                alignment = Alignment.TopEnd,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 4.dp, y = (-4).dp)
            )
        }
    }
}

@Composable
private fun WhatYouGetSection() {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(Color(0xff80cbc0), Color(0xffe0f2f0))
            )
        ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xffffffff),
                            Color(0xffedfaf9),
                        )
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "WHAT YOU GET",
                        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                        color = textColorDark,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color(0xffb2dfd8)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                PointsView("It's absolutely Free - no hidden charges!")
                PointsView("Visit multiple projects in one trip")
                PointsView("Keep the cab & driver for your entire trip")
            }
        }
    }
}

@Composable
private fun PointsView(text: String) {
    Column {
        Row {
            Icon(
                Icons.Default.Check, tint = Color(0xff009681),
                contentDescription = null
            )
            Text(
                text = text,
                color = textColorDark,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT)),
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun TimeSlotScreen(viewModel: SiteVisitFlowViewModel) {
    val tabTitles = viewModel.tabTitles
    val selectedTabIndex = viewModel.selectedTabIndex
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collectLatest { firstVisibleIndex ->
                val tabStartIndices = mutableListOf<Int>()
                var itemIndex = 0

                viewModel.timeSlots.forEach { slots ->
                    tabStartIndices.add(itemIndex)
                    itemIndex += slots.size
                }
                tabStartIndices.add(itemIndex)

                val newSelectedTab = tabStartIndices.indexOfLast { it <= firstVisibleIndex }
                if (newSelectedTab != viewModel.selectedTabIndex) {
                    viewModel.selectTab(newSelectedTab)
                }
            }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                val tabWidth = tabPositions[selectedTabIndex].width
                val indicatorWidth = tabWidth * 0.7f
                val indicatorOffset = (tabWidth - indicatorWidth) / 2

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.BottomStart)
                        .offset(x = tabPositions[selectedTabIndex].left + indicatorOffset)
                        .width(indicatorWidth)
                        .height(2.dp)
                        .background(Color(0xffd8232a), shape = RoundedCornerShape(50))
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        viewModel.selectTab(index)
                        coroutineScope.launch {
                            listState.scrollToItem(index * viewModel.timeSlots[index].size + 1)
                        }
                    },
                    text = {
                        Row {
                            Image(
                                painter = painterResource(
                                    if (tabTitles[index].equals("Morning", true)) R.drawable.morning_svg
                                    else if (tabTitles[index].equals(
                                            "Afternoon",
                                            true
                                        )
                                    ) R.drawable.afternoon_svg else R.drawable.night_icon
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                title,
                                fontSize = 13.sp,
                                color = if (selectedTabIndex == index) textColorDark else Color(0xff606060),
                                fontFamily = if (selectedTabIndex == index) FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)) else FontFamily(
                                    getFont(Constants.MONTSERRAT_MEDIUM)
                                )
                            )
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            state = listState,
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 60.dp)
        ) {
            val allItems = mutableListOf<Any>()

            viewModel.timeSlots.forEachIndexed { index, slots ->
                allItems.addAll(slots)
                if (index + 1 < viewModel.timeSlots.size) {
                    allItems.add(viewModel.tabTitles[index + 1])
                }
            }

            allItems.forEach { item ->
                item(span = {
                    when (item) {
                        is String -> GridItemSpan(2)
                        else -> GridItemSpan(1)
                    }
                }) {
                    when (item) {
                        is String -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Image(
                                    painter = painterResource(
                                        if (item.equals("Morning", true)) R.drawable.morning_svg
                                        else if (item.equals(
                                                "Afternoon",
                                                true
                                            )
                                        ) R.drawable.afternoon_svg else R.drawable.night_icon
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    item,
                                    fontSize = 14.sp, lineHeight = 20.sp,
                                    color = if (selectedTabIndex == viewModel.tabTitles.indexOf(item)) Color.Black else Color.Gray,
                                    fontFamily = if (selectedTabIndex == viewModel.tabTitles.indexOf(item))
                                        FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)) else FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
                                )
                            }
                        }

                        is TimeSlot -> {
                            TimeSlotItem(
                                item,
                                viewModel.selectedTime == item.id,
                                viewModel,
                                onClick = { viewModel.selectTime(item.id) }
                            )
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun TimeSlotItem(timeSlot: TimeSlot, isSelected: Boolean, viewModel: SiteVisitFlowViewModel, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .height(45.dp)
            .width(100.dp)
            .border(
                1.dp,
                if (isSelected) Color(0xffb2dfd8) else Color(0xffd7d7d7),
                RoundedCornerShape(20.dp)
            )
            .background(
                if (isSelected) Color(0xffedfaf9) else Color(0xffffffff),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {

        if (isSelected) {
            Text(
                text = timeSlot.time,
                color = textColorDark,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                fontSize = 14.sp
            )
            Image(
                painter = painterResource(id = R.drawable.ic_tick_green),
                contentDescription = null,
                alignment = Alignment.TopEnd,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 14.dp, y = -16.dp)
            )
        } else {
            Text(
                text = timeSlot.time,
                color = textColorDark,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            )
        }
    }
}
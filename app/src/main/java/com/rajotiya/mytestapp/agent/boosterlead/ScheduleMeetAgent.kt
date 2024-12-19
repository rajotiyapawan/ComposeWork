package com.rajotiya.mytestapp.agent.boosterlead

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont

/**
 * Created by Pawan Rajotiya on 03-12-2024.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleMeeting(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    ModalBottomSheet(
        modifier = modifier,
        containerColor = Color.White,
        dragHandle = null,
        onDismissRequest = { onDismiss() }) {
//        CustomDateTimePicker()
        ScheduleMeetUI()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScheduleMeetUI(modifier: Modifier = Modifier) {
    var daySelected by remember { mutableStateOf("") }
    var timeSelected by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFF4F4F4))
            .padding(top = 30.dp)
    ) {
        Text(
            text = "What time you’d be available to meet?",
            modifier = Modifier.padding(horizontal = 25.dp),
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(
                getFont(Constants.MONTSERRAT_SEMIBOLD)
            ),
            color = textColorDark
        )
        Spacer(modifier = Modifier.height(18.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(
                text = "Select Day",
                fontSize = 12.sp,
                fontFamily = FontFamily(
                    getFont(Constants.MONTSERRAT_SEMIBOLD)
                ),
                color = textColorLight
            )
            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                val list = listOf("+ Today", "Tomorrow", "This Saturday", "This Sunday", "Pick a Date")
                list.forEach { item ->
                    Row(
                        modifier = Modifier
                            .noRippleClick { daySelected = item }
                            .border(
                                width = 1.dp, shape = RoundedCornerShape(50),
                                color = if (item == daySelected) Color(0xff009681) else Color(0xffd7d7d7)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = textColorDark,
                            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
                        )
                        if (daySelected == item) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(imageVector = Icons.Default.Clear, contentDescription = null, modifier = Modifier.size(8.dp))
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(
                text = "Select Time",
                fontSize = 12.sp,
                fontFamily = FontFamily(
                    getFont(Constants.MONTSERRAT_SEMIBOLD)
                ),
                color = textColorLight
            )
            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                val list = listOf("9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "Pick a Time")
                list.forEach { item ->
                    Row(
                        modifier = Modifier
                            .noRippleClick { daySelected = item }
                            .border(
                                width = 1.dp, shape = RoundedCornerShape(50),
                                color = if (item == daySelected) Color(0xff009681) else Color(0xffd7d7d7)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = textColorDark,
                            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
                        )
                        if (daySelected == item) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(imageVector = Icons.Default.Clear, contentDescription = null, modifier = Modifier.size(8.dp))
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Reset",
                color = textColorLight,
                fontSize = 12.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_MEDIUM))
            )
            Box(
                modifier = Modifier
                    .background(color = mbRed, shape = RoundedCornerShape(50))
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = "Confirm Now", color = Color.White, fontSize = 14.sp, lineHeight = 30.sp, fontFamily = FontFamily(
                        getFont(Constants.MONTSERRAT_SEMIBOLD)
                    )
                )
            }
        }
    }
}

@Composable
fun CustomDateTimePicker() {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Text(text = "Select Date", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        DatePicker()
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Select Time", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        TimePicker()
    }
}


@Composable
fun TimePicker() {
    val hours = (1..12).map { it.toString().padStart(2, '0') }
    val minutes = (0..59).map { it.toString().padStart(2, '0') }
    val amPm = listOf("AM", "PM")

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        RollerPicker(items = hours, selectedIndex = 0) { selectedHour ->
            // Handle hour selection
        }
        RollerPicker(items = minutes, selectedIndex = 0) { selectedMinute ->
            // Handle minute selection
        }
        RollerPicker(items = amPm, selectedIndex = 0) { selectedAmPm ->
            // Handle AM/PM selection
        }
    }
}

@Composable
fun DatePicker() {
    val days = (1..31).map { it.toString().padStart(2, '0') }
    val months = (1..12).map { it.toString().padStart(2, '0') }
    val years = (2000..2030).map { it.toString() }
    val hours = (1..12).map { it.toString().padStart(2, '0') }
    val minutes = (0..59).map { it.toString().padStart(2, '0') }
    val amPm = listOf("AM", "PM")

    val (selectedDay, onDaySelection) = remember { mutableStateOf(0) }
    val (selectedMonth, onMonthSelection) = remember { mutableStateOf(0) }
    val (selectedHour, onHourSelection) = remember { mutableStateOf(0) }
    val (selectedMinute, onMinuteSelection) = remember { mutableStateOf(0) }
    val (selectedAMPM, onAMPMSelection) = remember { mutableStateOf(0) }


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        RollerPicker(items = days, selectedIndex = selectedDay) {             // Handle day selection
            onDaySelection(it)
        }
        RollerPicker(items = months, selectedIndex = selectedMonth) {
            // Handle month selection
            onMonthSelection(it)
        }
        RollerPicker(items = hours, selectedIndex = selectedHour) { onHourSelection(it)
            // Handle hour selection
        }
        RollerPicker(items = minutes, selectedIndex = selectedMinute) { onMinuteSelection(it)
            // Handle minute selection
        }
        RollerPicker(items = amPm, selectedIndex = selectedAMPM) { onAMPMSelection(it)
            // Handle AM/PM selection
        }
    }
}


@Composable
fun RollerPicker(
    items: List<String>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = maxOf(0, selectedIndex - 2))
    val visibleMiddleIndex = 1
    Box(
        modifier = Modifier
            .width(50.dp)
            .height(170.dp)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .width(50.dp)
                .height(170.dp) // Adjust height for the roller effect
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp) // Adjust item height
                        .padding(vertical = 4.dp)
                )
            }
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp) // Adjust item height
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        color = if (index == selectedIndex) Color(0xff00487c) else textColorDark
                    )
                }
            }
            item{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp) // Adjust item height
                        .padding(vertical = 4.dp)
                )
            }
        }
        // Underline for the middle item
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.6f)
                .height(2.dp)
                .background(Color.Blue)
        )
        // Top blur gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.White, Color.Transparent),
                        startY = 0f,
                        endY = 30f
                    )
                )
        )

        // Bottom blur gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.White),
                        startY = 0f,
                        endY = 30f
                    )
                )
        )
    }

    // Ensure the correct item is selected when scrolling
    LaunchedEffect(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset) {
        val centerItem = (listState.firstVisibleItemIndex + visibleMiddleIndex).coerceIn(0, items.lastIndex)
        if (centerItem != selectedIndex) {
            onItemSelected(centerItem)
        }
    }
}


package com.rajotiya.mytestapp.aob_revamp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.AOBViewModel
import com.rajotiya.mytestapp.aob_revamp.AppOnBoardingComposeActivity
import com.rajotiya.mytestapp.aob_revamp.ui.theme.disableButtonColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.filterOptionBorderColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.flowItemSelectedBorder
import com.rajotiya.mytestapp.aob_revamp.ui.theme.flowItemSelectedContainerColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.radioButtonSelected
import com.rajotiya.mytestapp.aob_revamp.ui.theme.radioButtonUnselected

/**
 * Created by Pawan Rajotiya on 09-08-2024.
 */

@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    viewModel: AOBViewModel = AppOnBoardingComposeActivity.localViewModelCompositionLocal.current,
    onSelection: (id: Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        TopView(modifier = Modifier.fillMaxWidth())
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(y = (-20).dp)
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .padding(top = 25.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.TopCenter)
            ) {
                LocalityView(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(18.dp))
                MiddleView()
            }
            BottomView(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun LocalityView(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .padding(horizontal = 10.dp, vertical = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val list = arrayListOf("Noida", "Sec 72", "Sec 70")
        items(list) { item ->
            Box(
                modifier = Modifier
                    .border(width = 1.dp, color = flowItemSelectedBorder, shape = RoundedCornerShape(50))
                    .background(color = flowItemSelectedContainerColor, shape = RoundedCornerShape(50))
                    .padding(horizontal = 14.dp, vertical = 7.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = item)
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(imageVector = Icons.Outlined.Clear, contentDescription = null, modifier = Modifier.size(12.dp))
                }
            }
        }
    }
}

@Composable
private fun TopView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(start = 20.dp, end = 16.dp, top = 12.dp, bottom = 44.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null, tint = Color.White)
            Box(
                modifier = Modifier
                    .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(text = "Skip", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Let’s find you properties matching your requirements",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MiddleView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(ScrollState(0))
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        // State to keep track of the selected tab index
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        val tabList = arrayListOf("Residential", "Commercial")
        TabRow(selectedTabIndex = 0) {
            tabList.forEachIndexed() { index, tab ->
                Tab(selected = false, onClick = { selectedTabIndex = index }) {
                    Text(text = tab)
                }
            }
        }

        // Display the content screen corresponding to the selected tab
        when (selectedTabIndex) {
            0 -> ResidentialFilters(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            )
        }
    }
}

@Composable
private fun ResidentialFilters(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(bottom = 80.dp)
    ) {
        Text(text = "Property Type*")
        Spacer(modifier = Modifier.height(10.dp))
        val list = arrayListOf("Office", "Parents/Friends Home", "Kid's School", "None")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(list) { index, item ->
                Column(
                    modifier = Modifier
                        .size(100.dp)
                        .border(width = 1.dp, color = filterOptionBorderColor, shape = RoundedCornerShape(8.dp))
                        .padding(top = 20.dp, start = 8.dp, end = 8.dp, bottom = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_prime_bitmap_chat),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = item, fontSize = 12.sp, lineHeight = 12.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        Text(text = "No. of Bedrooms*")
        Spacer(modifier = Modifier.height(10.dp))
        val bdList = arrayListOf("Office", "Parents/Friends Home", "Kid's School", "None")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(list) { index, item ->
                Column(
                    modifier = Modifier
                        .border(width = 1.dp, color = filterOptionBorderColor, shape = RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = item, fontSize = 12.sp, lineHeight = 12.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        Text(text = "Budget Range*")
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            OutlinedTextField(
                modifier = Modifier.weight(1F),
                value = "",
                onValueChange = {},
                readOnly = true,
                placeholder = { Text(text = "Minimum") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null
                    )
                })
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1F),
                value = "",
                onValueChange = {},
                readOnly = true,
                placeholder = { Text(text = "Maximum") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null
                    )
                })
        }
        var sliderPosition by remember { mutableStateOf(0f..100f) }
        RangeSlider(
            value = sliderPosition,
            steps = 0,
            onValueChange = { range -> sliderPosition = range },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(text = "Budget Range*")
        Spacer(modifier = Modifier.height(8.dp))
        val (optionSelected, onOptionSelected) = remember { mutableStateOf("") }
        Row {
            Row(
                Modifier
                    .weight(1f)
                    .selectable(
                        selected = ("Ready to Move" == optionSelected),
                        onClick = {
                            onOptionSelected("Ready to Move")
                        }
                    )
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    modifier = Modifier.size(20.dp),
                    selected = ("Ready to Move" == optionSelected),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = radioButtonSelected,
                        unselectedColor = radioButtonUnselected
                    )
                )
                Text(
                    text = "Ready to Move",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 12.sp
                )
            }
            Row(
                Modifier
                    .weight(1f)
                    .selectable(
                        selected = ("Under Construction" == optionSelected),
                        onClick = {
                            onOptionSelected("Under Construction")
                        }
                    )
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    modifier = Modifier.size(20.dp),
                    selected = ("Under Construction" == optionSelected),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = radioButtonSelected,
                        unselectedColor = radioButtonUnselected
                    )
                )
                Text(
                    text = "Under Construction",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun BottomView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier

            .offset(y = (20).dp)
//                .padding(top = 8.dp)
            .shadow(elevation = 12.dp, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = disableButtonColor, shape = RoundedCornerShape(50))
        ) {
            Text(
                text = "Next", modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp), textAlign = TextAlign.Center
            )
        }
    }
}
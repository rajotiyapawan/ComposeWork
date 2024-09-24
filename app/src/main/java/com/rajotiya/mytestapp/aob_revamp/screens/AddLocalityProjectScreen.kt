package com.rajotiya.mytestapp.aob_revamp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.AOBViewModel
import com.rajotiya.mytestapp.aob_revamp.AppOnBoardingComposeActivity
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight

/**
 * Created by Pawan Rajotiya on 06-08-2024.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddLocalityProjectScreen(
    modifier: Modifier = Modifier,
    viewModel: AOBViewModel = AppOnBoardingComposeActivity.localViewModelCompositionLocal.current,
    onSelection: (id: Int) -> Unit
) {
    val optionSelected = remember { mutableStateOf(0) }
    Column(modifier = modifier.fillMaxHeight()) {
        TopView(modifier = modifier)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color(0xFFD8D8D8), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null, tint = Color(0xFF606060))
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Search City, Locality, Project", fontSize = 14.sp, color = Color(0xFF606060))
            }
        }
        BodyView(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(horizontal = 20.dp),
            viewModel = viewModel
        )
    }
}

@Composable
private fun TopView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(all = 16.dp)
    ) {
        Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null, tint = textColorDark)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Enter your preferred locality or project",
            style = MaterialTheme.typography.titleMedium,
            color = textColorDark
        )
        Text(text = "For better property suggestions", fontSize = 14.sp, color = textColorLight, lineHeight = 20.sp)
    }
}

@Composable
private fun BodyView(modifier: Modifier = Modifier, viewModel: AOBViewModel) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "Popular Localities in Noida",
            modifier = Modifier.fillMaxWidth(),
            color = textColorLight,
            fontSize = 12.sp, lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(0.dp, 500.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
//                userScrollEnabled = false,
        ) {
            val cityList = arrayListOf(
                "New Delhi",
                "Mumbai",
                "Bengaluru",
                "Gurgaon"
            )
            items(cityList) { city ->
                Column {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "+ $city", fontSize = 12.sp, lineHeight = 20.sp, color = textColorDark)
                        Text(
                            text = "₹4.0K /sqft",
                            color = textColorExtraLight,
                            fontSize = 10.sp, lineHeight = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color(0xFFD8D8D8))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Popular Localities in Noida",
            modifier = Modifier.fillMaxWidth(),
            color = textColorLight,
            fontSize = 12.sp, lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(0.dp, 500.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
//                userScrollEnabled = false,
        ) {
            val cityList = arrayListOf(
                "New Delhi",
                "Mumbai",
                "Bengaluru",
                "Gurgaon"
            )
            items(cityList) { city ->
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = "+ $city", fontSize = 12.sp, lineHeight = 20.sp, color = textColorDark)
                        Text(text = "Sector 70", fontSize = 10.sp, lineHeight = 20.sp, color = textColorExtraLight)
                    }
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = "₹4.0K /sqft",
                        color = textColorExtraLight,
                        fontSize = 10.sp, lineHeight = 20.sp
                    )
                }
            }
        }
    }
}
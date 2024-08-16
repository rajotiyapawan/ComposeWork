package com.rajotiya.mytestapp.aob_revamp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.AOBViewModel
import com.rajotiya.mytestapp.aob_revamp.AppOnBoardingComposeActivity
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import kotlinx.coroutines.awaitCancellation

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
    LazyColumn(modifier = modifier.fillMaxHeight()) {
        item {
            TopView(modifier = modifier)
        }
        stickyHeader {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp)
                    .zIndex(8F)
                    .background(color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 38.dp)
                        .border(width = 1.dp, color = Color(0x99909090), shape = RoundedCornerShape(50))
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null, tint = Color(0xFF606060))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Search City, Locality, Project", fontSize = 14.sp, color = Color(0xFF606060))
                }
            }
        }

        item {
            BodyView(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(horizontal = 20.dp),
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun TopView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(all = 24.dp)
            .padding(bottom = 20.dp)
    ) {
        Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Which city do you plan to buy in", style = MaterialTheme.typography.titleMedium, color = Color.White)
    }
}

@Composable
private fun BodyView(modifier: Modifier = Modifier, viewModel: AOBViewModel) {
    val gridState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        gridState.scroll(scrollPriority = MutatePriority.PreventUserInput) { awaitCancellation() }
    }
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "Popular Cities",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = textColorDark,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(0.dp, 500.dp),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(24.dp),
//                userScrollEnabled = false,
        ) {
            val cityList = arrayListOf(
                "New Delhi",
                "Mumbai",
                "Bengaluru",
                "Gurgaon",
                "Ghaziabad",
                "Noida",
                "Kolkata",
                "Chennai",
                "Hyderabad",
                "Navi Mumbai",
                "Greater Noida",
                "Pune"
            )
            items(cityList) { city ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_prime_bitmap_chat), contentDescription = null)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = city,
                        color = textColorDark,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(28.dp))
        Text(text = "All Cities", color = textColorExtraLight, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(12.dp))
        repeat(times = 40) {
            Text(text = "Ajmer", color = textColorDark)
        }
    }
}
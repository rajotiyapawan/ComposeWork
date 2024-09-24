package com.rajotiya.mytestapp.aob_revamp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.AOBViewModel
import com.rajotiya.mytestapp.aob_revamp.AppOnBoardingComposeActivity
import com.rajotiya.mytestapp.aob_revamp.models.OptionItem
import com.rajotiya.mytestapp.aob_revamp.ui.theme.aobIconBg
import com.rajotiya.mytestapp.aob_revamp.ui.theme.buyRentContainerColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.containerBorder
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.utils.AobGAEvents
import com.rajotiya.mytestapp.aob_revamp.utils.BuyProperty
import com.rajotiya.mytestapp.aob_revamp.utils.ManageExistingProperty
import com.rajotiya.mytestapp.aob_revamp.utils.PostNewProperty
import com.rajotiya.mytestapp.aob_revamp.utils.RentProperty
import com.rajotiya.mytestapp.aob_revamp.utils.SearchProperty
import com.rajotiya.mytestapp.aob_revamp.utils.SellProperty
import com.rajotiya.mytestapp.aob_revamp.utils.preventMultipleClicks
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont
import com.til.mb.aob_v2.utils.AobUserEvents

/**
 * Created by Pawan Rajotiya on 05-08-2024.
 */


@Composable
fun InitialSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: AOBViewModel = AppOnBoardingComposeActivity.localViewModelCompositionLocal.current
) {
    val optionSelected = remember { mutableIntStateOf(0) }
    val clickCounter = remember { mutableIntStateOf(0) }
    Column(modifier = modifier.fillMaxHeight().background(color = mbRed)) {
        TopView(modifier = modifier.zIndex(1F))
        OptionView(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
//                .offset(y = (-40).dp)
                .zIndex(2F)
                .shadow(
                    elevation = 8.dp, // Adjust the elevation value as needed
                    shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp),
                    ambientColor = Color(0x33000000),
                    spotColor = Color(0x33000000),
                )
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)),
            viewModel = viewModel,
            optionSelected = optionSelected,
            clickCounter = clickCounter
        )
    }
    LaunchedEffect(key1 = clickCounter.intValue) {
        viewModel.sendUserEvent(AobUserEvents.InitialSelection(optionSelected.intValue))
        if (optionSelected.intValue > 0) {
            viewModel.sendGaEvent(AobGAEvents.InitialOptionSelected(optionSelected.intValue))
        }
    }
}

@Composable
private fun TopView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(all = 24.dp)
            .padding(bottom = 10.dp)
    ) {
        Row {
            Image(painter = painterResource(id = R.drawable.cool_smiley), contentDescription = null)
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = "Hey there,", color = Color.White, style = MaterialTheme.typography.titleSmall)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "What’s on your mind?", style = MaterialTheme.typography.titleLarge, color = Color.White)
    }
}

@Composable
private fun OptionView(
    modifier: Modifier = Modifier,
    viewModel: AOBViewModel,
    optionSelected: MutableState<Int>,
    clickCounter: MutableState<Int>
) {
    val optionList = viewModel.aobOptions.collectAsState()
    val localOptionSelection = remember { mutableIntStateOf(0) }
    LaunchedEffect(key1 = localOptionSelection.intValue) {
        if (localOptionSelection.intValue > 0) {
            viewModel.sendGaEvent(AobGAEvents.InitialOptionSelected(localOptionSelection.intValue))
        }
    }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(optionList.value) { item ->
                GetOptionItem(item = item, optionSelected = optionSelected, clickCounter = clickCounter, localOptionSelection)
            }
        }
    }
}

@Composable
private fun GetOptionItem(
    item: OptionItem,
    optionSelected: MutableState<Int>,
    clickCounter: MutableState<Int>,
    localOptionSelection: MutableIntState
) {
    if (localOptionSelection.intValue == item.id && localOptionSelection.intValue == SearchProperty) { // for Search Property there is need to show additional options
        PropertySearchOptionAfterSelection(
            item = item,
            modifier = Modifier.shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
        ) {
            optionSelected.value = it
            clickCounter.value += 1
        }
    } else if (localOptionSelection.intValue == item.id && localOptionSelection.intValue == SellProperty) { // for Sell Property there is need to show additional options
        PropertySellOptionAfterSelection(
            item = item,
            modifier = Modifier.shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
        ) {
            optionSelected.value = it
            clickCounter.value += 1
        }
    } else {
        OptionItem(
            item = item, modifier = if (localOptionSelection.intValue == 0) {
                Modifier.shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
            } else Modifier
        ) {
            if (it == SearchProperty || it == SellProperty) {
                localOptionSelection.intValue = it
            } else {
                optionSelected.value = it
                clickCounter.value += 1
            }
        }
    }
}

@Composable
private fun OptionItem(modifier: Modifier = Modifier, item: OptionItem, onClick: (id: Int) -> Unit) {
    Row(modifier = modifier
//        .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
        .fillMaxWidth()
        .preventMultipleClicks { onClick(item.id) }
        .border(width = 1.dp, color = containerBorder, shape = RoundedCornerShape(12.dp))
        .background(color = Color.White, shape = RoundedCornerShape(12.dp))
        .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = aobIconBg)
                .padding(8.dp)
                .size(24.dp)
        ) {
            Image(painter = painterResource(id = item.icon), contentDescription = null, contentScale = ContentScale.None)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = item.title, style = MaterialTheme.typography.labelLarge, color = textColorDark)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.subTitle, style = MaterialTheme.typography.labelSmall, color = textColorLight)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = null, tint = mbRed)
    }
}

@Composable
private fun PropertySearchOptionAfterSelection(modifier: Modifier = Modifier, item: OptionItem, onClick: (id: Int) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Column {
        Row(
            modifier = modifier
//                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .border(width = 1.dp, color = containerBorder, shape = RoundedCornerShape(12.dp))
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .padding(bottom = 40.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = aobIconBg)
                    .padding(8.dp)
            ) {
                Image(painter = painterResource(id = item.icon), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = item.title, style = MaterialTheme.typography.labelLarge, color = textColorDark)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.subTitle, style = MaterialTheme.typography.labelSmall, color = textColorLight)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(modifier = Modifier
                .weight(1f)
                .offset(y = (-40).dp)
                .preventMultipleClicks { onClick(BuyProperty) }
//                .clickable(interactionSource = interactionSource, indication = null) { onClick(BuyProperty) }
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                .background(color = buyRentContainerColor, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 18.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Buy", fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)), fontSize = 16.sp
                )
                Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = null, tint = mbRed)
            }
            Row(modifier = Modifier
                .weight(1f)
                .offset(y = (-40).dp)
                .preventMultipleClicks { onClick(RentProperty) }
//                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
//                    onClick(
//                        RentProperty
//                    )
//                }
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                .background(color = buyRentContainerColor, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 18.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Rent", fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)), fontSize = 16.sp
                )
                Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = null, tint = mbRed)
            }
        }
    }
}

@Composable
private fun PropertySellOptionAfterSelection(modifier: Modifier = Modifier, item: OptionItem, onClick: (id: Int) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Column {
        Row(
            modifier = modifier
//                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .border(width = 1.dp, color = containerBorder, shape = RoundedCornerShape(12.dp))
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .padding(bottom = 40.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = aobIconBg)
                    .padding(8.dp)
            ) {
                Image(painter = painterResource(id = item.icon), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = item.title, style = MaterialTheme.typography.labelLarge, color = textColorDark)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.subTitle, style = MaterialTheme.typography.labelMedium, color = textColorLight)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(modifier = Modifier
                .weight(1f)
                .offset(y = (-40).dp)
                .preventMultipleClicks { onClick(PostNewProperty) }
//                .clickable(interactionSource = interactionSource, indication = null) { onClick(PostNewProperty) }
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                .background(color = buyRentContainerColor, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Post New Property",
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight,
                    contentDescription = null,
                    tint = mbRed
                )
            }
            Row(modifier = Modifier
                .weight(1f)
                .offset(y = (-40).dp)
                .preventMultipleClicks { onClick(ManageExistingProperty) }
//                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
//                    onClick(ManageExistingProperty)
//                }
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                .background(color = buyRentContainerColor, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Manage Existing", fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)), fontSize = 16.sp,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = null, tint = mbRed)
            }
        }
    }
}
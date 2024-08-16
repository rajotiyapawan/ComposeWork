package com.rajotiya.mytestapp.aob_revamp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rajotiya.mytestapp.aob_revamp.AOBViewModel
import com.rajotiya.mytestapp.aob_revamp.AppOnBoardingComposeActivity
import com.rajotiya.mytestapp.aob_revamp.BuyProperty
import com.rajotiya.mytestapp.aob_revamp.HomeInteriors
import com.rajotiya.mytestapp.aob_revamp.HomeLoans
import com.rajotiya.mytestapp.aob_revamp.PropWorth
import com.rajotiya.mytestapp.aob_revamp.RentProperty
import com.rajotiya.mytestapp.aob_revamp.SearchProperty
import com.rajotiya.mytestapp.aob_revamp.SellProperty
import com.rajotiya.mytestapp.aob_revamp.models.OptionItem
import com.rajotiya.mytestapp.aob_revamp.ui.theme.buyRentContainerColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.containerBorder
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight

/**
 * Created by Pawan Rajotiya on 05-08-2024.
 */

@Composable
fun InitialSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: AOBViewModel = AppOnBoardingComposeActivity.localViewModelCompositionLocal.current,
    onSelection: (id:Int) -> Unit
) {
    val optionSelected = remember { mutableStateOf(0) }
    Column(modifier = modifier) {
        TopView(modifier = modifier)
        OptionView(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(y = (-40).dp)
                .zIndex(8F)
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)),
            viewModel = viewModel,
            optionSelected = optionSelected
        )
    }
    HandleOptionSelection(optionSelected = optionSelected, onSelection)
}

@Composable
private fun TopView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(all = 24.dp)
            .padding(bottom = 50.dp)
    ) {
        Row {
            Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = null)
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = "Hey there,", color = Color.White, style = MaterialTheme.typography.titleSmall)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "What’s on your mind?", style = MaterialTheme.typography.titleLarge, color = Color.White)
    }
}

@Composable
private fun OptionView(modifier: Modifier = Modifier, viewModel: AOBViewModel, optionSelected: MutableState<Int>) {
    val optionList = remember { viewModel.getOptionList() }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(optionList) { item ->
                if (optionSelected.value == item.id && optionSelected.value == SearchProperty) { // for Search Property there is need to show additional options
                    PropertySearchOptionAfterSelection(item = item) {
                        optionSelected.value = it
                    }
                } else {
                    OptionItem(item = item) {
                        optionSelected.value = it
                    }
                }
            }
        }
    }
}

@Composable
private fun OptionItem(modifier: Modifier = Modifier, item: OptionItem, onClick: (id: Int) -> Unit) {
    Row(
        modifier = modifier
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .clickable { onClick(item.id) }
            .border(width = 1.dp, color = containerBorder, shape = RoundedCornerShape(12.dp))
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = item.icon), contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = item.title, style = MaterialTheme.typography.labelLarge, color = textColorDark)
            Text(text = item.subTitle, style = MaterialTheme.typography.labelMedium, color = textColorLight)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = null, tint = mbRed)
    }
}

@Composable
private fun PropertySearchOptionAfterSelection(modifier: Modifier = Modifier, item: OptionItem, onClick: (id: Int) -> Unit) {
    Column {
        Row(
            modifier = modifier
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .border(width = 1.dp, color = containerBorder, shape = RoundedCornerShape(12.dp))
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .padding(bottom = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = item.icon), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = item.title, style = MaterialTheme.typography.labelLarge, color = textColorDark)
                Text(text = item.subTitle, style = MaterialTheme.typography.labelMedium, color = textColorLight)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = null, tint = mbRed)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClick(BuyProperty) }
                    .offset(y = (-40).dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                    .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                    .background(color = buyRentContainerColor, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Buy",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = null, tint = mbRed)
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClick(RentProperty) }
                    .offset(y = (-40).dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                    .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                    .background(color = buyRentContainerColor, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Rent",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = null, tint = mbRed)
            }
        }
    }
}

@Composable
private fun HandleOptionSelection(optionSelected: MutableState<Int>, onSelection: (id: Int) -> Unit) {
    when (optionSelected.value) {
        BuyProperty -> { onSelection(BuyProperty) }
        RentProperty -> { onSelection(RentProperty) }
        SellProperty -> { onSelection(SellProperty) }
        PropWorth -> { onSelection(PropWorth) }
        HomeLoans -> { onSelection(HomeLoans) }
        HomeInteriors -> { onSelection(HomeInteriors) }
    }
}
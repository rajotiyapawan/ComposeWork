package com.rajotiya.mytestapp.poi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R

/**
 * Created by Pawan Rajotiya on 09-07-2024.
 */

@Composable
fun POIOptionScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Box(modifier = Modifier
                .padding(10.dp)
                .clickable {}) {
                Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Let’s Personalise your Search", fontSize = 14.sp, lineHeight = 24.sp, color = Color(0xff606060))
            Text(
                text = "Are you searching near a specific landmark?",
                fontSize = 18.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center, color = Color(0xff303030)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "(Example: I am searching near by my Office", color = Color(0xff606060),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Box(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xffd8232a), shape = RoundedCornerShape(8.dp))
                        .padding(vertical = 12.dp, horizontal = 40.dp)
                ) {
                    Text(text = "No", color = Color(0xffd8232a))
                }
                Box(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xffd8232a), shape = RoundedCornerShape(8.dp))
                        .padding(vertical = 12.dp, horizontal = 40.dp)
                ) {
                    Text(text = "Yes", color = Color(0xffd8232a))
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun POILocalitySearch(modifier: Modifier = Modifier) {
    val (value, onValueChange) = remember { mutableStateOf("") }
    val localitySelected = remember { mutableStateOf(true) }
    val (tagSelected, onTagSelection) = remember { mutableStateOf("") }
    val currentFocus = LocalFocusManager.current
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.White)
            .clickable { currentFocus.clearFocus() }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Box(modifier = Modifier
                .padding(10.dp)
                .clickable {}) {
                Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
        ) {
            Text(text = "Enter the locality of your Landmark", fontSize = 18.sp, lineHeight = 28.sp, color = Color(0xff303030))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value, onValueChange = onValueChange, colors = defaultTextFieldColors(), textStyle = TextStyle(
                    color = Color(0xff303030), fontSize = 14.sp, lineHeight = 32.sp
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color(0xffe8e8e8), shape = RoundedCornerShape(8.dp))
                    .background(color = Color(0xfffbfbfb), shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(text = "Tag this Landmark as:", color = Color(0xff909090), fontSize = 12.sp, lineHeight = 28.sp)
                Spacer(modifier = Modifier.height(8.dp))
                val list = listOf("Office", "Parents/FriendsHome", "School", "None")
                FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    list.forEach { tag ->
                        if (localitySelected.value) {
                            val bgColor = if (tag == tagSelected) Color(0xfffbe9e9) else Color.White
                            val borderColor = if (tag == tagSelected) Color(0xffe8e8e8) else Color(0xffd7d7d7)
                            val textColor = if (tag == tagSelected) Color(0xff303030) else Color(0xff606060)
                            Box(
                                modifier = Modifier
                                    .selectable(
                                        selected = tag == tagSelected,
                                        indication = null,
                                        interactionSource = MutableInteractionSource(),
                                        onClick = { onTagSelection(tag) })
                                    .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(4.dp))
                                    .background(color = bgColor, shape = RoundedCornerShape(4.dp))
                                    .padding(horizontal = 12.dp)
                            ) {
                                Text(text = tag, fontSize = 14.sp, lineHeight = 32.sp, color = textColor)
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .background(color = Color(0xffe8e8e8), shape = RoundedCornerShape(4.dp))
                                    .padding(horizontal = 12.dp)
                            ) {
                                Text(text = tag, fontSize = 14.sp, lineHeight = 32.sp, color = Color(0xff909090))
                            }
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun defaultTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        cursorColor = Color(0xff303030),
        focusedIndicatorColor = Color(0xffd7d7d7),
        unfocusedIndicatorColor = Color(0xffd7d7d7),
        focusedTextColor = Color(0xff303030),
        unfocusedTextColor = Color(0xff303030),
        disabledTextColor = Color.Transparent,
        errorTextColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        errorCursorColor = Color.Transparent,
        selectionColors = LocalTextSelectionColors.current,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = Color.Transparent,
        unfocusedLeadingIconColor = Color.Transparent,
        disabledLeadingIconColor = Color.Transparent,
        errorLeadingIconColor = Color.Transparent,
        focusedTrailingIconColor = Color.Transparent,
        unfocusedTrailingIconColor = Color.Transparent,
        disabledTrailingIconColor = Color.Transparent,
        errorTrailingIconColor = Color.Transparent,
        focusedLabelColor = Color(0xff606060),
        unfocusedLabelColor = Color(0xff606060),
        disabledLabelColor = Color.Transparent,
        errorLabelColor = Color.Transparent,
        focusedPlaceholderColor = Color.Transparent,
        unfocusedPlaceholderColor = Color.Transparent,
        disabledPlaceholderColor = Color.Transparent,
        errorPlaceholderColor = Color.Transparent,
        focusedSupportingTextColor = Color.Transparent,
        unfocusedSupportingTextColor = Color.Transparent,
        disabledSupportingTextColor = Color.Transparent,
        errorSupportingTextColor = Color.Transparent,
        focusedPrefixColor = Color.Transparent,
        unfocusedPrefixColor = Color.Transparent,
        disabledPrefixColor = Color.Transparent,
        errorPrefixColor = Color.Transparent,
        focusedSuffixColor = Color.Transparent,
        unfocusedSuffixColor = Color.Transparent,
        disabledSuffixColor = Color.Transparent,
        errorSuffixColor = Color.Transparent,
    )
}

@Composable
fun POILocalitySelection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Box(modifier = Modifier
                .padding(10.dp)
                .clickable {}) {
                Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Suggested nearby localities to", fontSize = 18.sp, lineHeight = 28.sp, color = Color(0xff303030))
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp, color = Color(0xff80cbc0), shape = RoundedCornerShape(10.dp)
                    )
                    .background(color = Color(0xffedfaf9), shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Sector 2, Noida",
                    fontSize = 16.sp,
                    lineHeight = 20.sp, color = Color(0xff303030)
                )
                Text(
                    text = "Are you searching near a specific landmark?",
                    fontSize = 12.sp,
                    lineHeight = 16.sp, color = Color(0xff606060)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        val numbers = (0..5).toList()
        Box {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = Color(0xfff5f5f5))
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                columns = GridCells.Fixed(2)
            ) {
                itemsIndexed(numbers) { index, _ ->
                    val modifier = Modifier.padding(
                        top = if (index < 2) 12.dp else 0.dp,
                        bottom = if (index == numbers.size - 1) 65.dp else 0.dp
                    )
                    Box(
                        modifier = modifier
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = Color(0xff009681),
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp)
                                .align(Alignment.TopEnd)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopCenter)
                                .padding(top = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(color = Color.Gray, shape = CircleShape)
                                    .size(55.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_prime_bitmap_chat),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .offset(y = (-12).dp)
                                    .border(width = 1.dp, color = Color(0xffffde82), shape = RoundedCornerShape(4.dp))
                                    .background(color = Color(0xfffffcf2), shape = RoundedCornerShape(4.dp))
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_selected_one),
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(text = "4.5", fontSize = 10.sp, lineHeight = 16.sp, color = Color.Black)
                            }

                            Text(text = "Noida Extension", fontSize = 14.sp, lineHeight = 20.sp, color = Color(0xff303030))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.LocationOn,
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp),
                                    tint = Color(0xff009681)
                                )
                                Text(maxLines = 1,
                                    text = "2 km from Parents/Friends Home School",
                                    fontSize = 10.sp,
                                    lineHeight = 16.sp,
                                    color = Color(0xff009681),
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "2,3 BHK  | 2 Cr Onwards",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = Color(0xff606060)
                            )
                            Text(text = "₹4000 - 6000/sqft", fontSize = 12.sp, lineHeight = 20.sp, color = Color(0xff303030))
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Color(0xfffff7e1),
                                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                                    )
                                    .padding(horizontal = 4.dp, vertical = 2.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "290 Properties | 10 Projects",
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    color = Color(0xff303030)
                                )

                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(top = 3.dp)
                    .shadow(elevation = 8.dp)
                    .background(color = Color.White)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xffd8232a), shape = RoundedCornerShape(4.dp))
                        .padding(vertical = 10.dp), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Show Properties", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestPOI() {
    POILocalitySelection()
}
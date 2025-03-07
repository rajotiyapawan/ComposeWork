package com.rajotiya.mytestapp.sitevisit_flow.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

@Composable
fun SVPickUpLocationScreen(modifier: Modifier = Modifier) {
    val (textValue, onValueChange) = remember { mutableStateOf("") }
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
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp), contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    "Skip",
                    textDecoration = TextDecoration.Underline,
                    color = textColorLight,
                    fontSize = 14.sp, lineHeight = 24.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
                )
            }
            Spacer(Modifier.height(12.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text("Enter Pickup Location", color = textColorDark, fontSize = 18.sp, lineHeight = 24.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))
                Spacer(Modifier.height(3.dp))
                Row {
                    Text("Sun 9 Feb  | 9:00 AM", color = textColorLight, fontSize = 14.sp, lineHeight = 19.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR))
                    Spacer(Modifier.width(6.dp))
                    Text(
                        "Edit", modifier = Modifier.noRippleClick { }, color = mbRed, textDecoration = TextDecoration.Underline, fontSize = 14.sp, lineHeight = 19.sp, fontFamily = getFontFamily(
                            Constants
                                .MONTSERRAT_REGULAR
                        )
                    )
                }
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.Gray)
                .padding(20.dp)
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange,
                textStyle = TextStyle(fontSize = 14.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD), color = textColorLight),
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter Locality/Project/Landmark", fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM), fontSize = 14.sp, color = textColorLight) },
                trailingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color(0xff909090), unfocusedBorderColor = Color(0xffd7d7d7)
                )
            )
            if (textValue.isEmpty()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp)
                        .background(color = Color(0xffFFF7E1), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Info, contentDescription = null, modifier = Modifier.size(10.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Share your exact location at the time of pickup", fontSize = 12.sp, lineHeight = 24.sp, color = textColorDark, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR))
                }
            }
            SearchResultsView(modifier = Modifier.fillMaxWidth()) {
                onValueChange(it)
            }
        }
    }
}

@Composable
private fun SearchResultsView(modifier: Modifier = Modifier, onSearchItemSelected: (item: String) -> Unit) {
    val list = listOf("Kailash Hospital, Sec 27, Noida", "Kailash Colony, Sec 127, Noida", "Kaily Town, Sec 27, Noida")
    LazyColumn(
        modifier = modifier
            .heightIn(max = 300.dp)
            .border(width = 1.dp, color = Color(0xffd7d7d7), shape = RoundedCornerShape(8.dp))
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(start = 16.dp, end = 11.dp),
        contentPadding = PaddingValues(bottom = 10.dp)
    ) {
        itemsIndexed(list) { index, item ->
            Column(Modifier
                .fillMaxWidth()
                .noRippleClick { onSearchItemSelected(item) }
                .padding(top = 16.dp, bottom = 15.dp)) {
                Text(item, fontSize = 14.sp, color = textColorLight, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR))
            }
            if (index < list.size - 1) {
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(0xffdcdcdc))
            }
        }
    }
}
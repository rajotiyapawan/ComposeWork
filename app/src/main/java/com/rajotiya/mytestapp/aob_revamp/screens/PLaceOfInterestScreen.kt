package com.rajotiya.mytestapp.aob_revamp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.AOBViewModel
import com.rajotiya.mytestapp.aob_revamp.AppOnBoardingComposeActivity
import com.rajotiya.mytestapp.aob_revamp.ui.theme.disableButtonColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.optionContainerColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textFieldColor

/**
 * Created by Pawan Rajotiya on 07-08-2024.
 */

@Composable
fun PLaceOfInterestScreen(
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
                .padding(top = 20.dp)
        ) {
            MiddleView(modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.TopCenter))
            BottomView(modifier = Modifier.align(Alignment.BottomCenter))
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
            text = "Help us get you more property recommendations",
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
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        Text(
            text = "Any specific place of interest that you prefer to live around?",
            fontSize = 16.sp,
            color = textColorDark,
            lineHeight = 20.sp, fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        val list = arrayListOf("Office", "Parents/Friends Home", "Kid's School", "None")
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            list.forEach { item ->
                Box(
                    modifier = Modifier
                        .border(width = 1.dp, color = optionContainerColor, shape = RoundedCornerShape(50))
                        .padding(horizontal = 14.dp, vertical = 7.dp)
                ) {
                    Text(text = item)
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Where is your Kid’s School located?",
            fontSize = 16.sp,
            color = textColorDark,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = "", onValueChange = {}, placeholder = {
            Text(
                text = "Enter Locality Like: Sec 50"
            )
        }, shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = textFieldColor,
                unfocusedBorderColor = textFieldColor,
                disabledBorderColor = textFieldColor,
                cursorColor = textColorDark
            )
        )
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
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rajotiya.mytestapp.aob_revamp.AOBViewModel
import com.rajotiya.mytestapp.aob_revamp.AppOnBoardingComposeActivity
import com.rajotiya.mytestapp.aob_revamp.ui.theme.disableButtonColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.optionContainerColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight

/**
 * Created by Pawan Rajotiya on 07-08-2024.
 */

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BuyFlowMoreProductsScreen(
    modifier: Modifier = Modifier,
    viewModel: AOBViewModel = AppOnBoardingComposeActivity.localViewModelCompositionLocal.current,
    onSelection: (id: Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(start = 20.dp, end = 16.dp, top = 12.dp, bottom = 44.dp)
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
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
                text = "Let’s help you with your home buying journey",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-20).dp)
                .zIndex(8F)
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            Text(
                text = "Select products you are interested in exploring later.",
                fontSize = 14.sp,
                color = textColorLight,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            val list = arrayListOf(
                "Home Loans",
                "Site Visit Assistance",
                "New Project updates",
                "Market Insights",
                "Home Interiors",
                "Price Trends"
            )
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                list.forEach { item ->
                    Box(
                        modifier = Modifier
                            .border(width = 1.dp, color = optionContainerColor, shape = RoundedCornerShape(50))
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Text(text = "+ ${item}")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = disableButtonColor, shape = RoundedCornerShape(50))
            ) {
                Text(text = "Next", modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), textAlign = TextAlign.Center)
            }
        }
    }
}
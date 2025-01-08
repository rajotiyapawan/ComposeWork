package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.loyalty.LoyaltyLandingActivity
import com.rajotiya.mytestapp.loyalty.LoyaltyViewModel
import com.rajotiya.mytestapp.loyalty.models.TextModel
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.TextWithPlaceholders
import com.rajotiya.mytestapp.utility.getComposeColor
import com.rajotiya.mytestapp.utility.getComposeImageFromUrl
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 08-01-2025.
 */

@Composable
fun LoyaltyTaskView(
    modifier: Modifier = Modifier,
    viewModel: LoyaltyViewModel = LoyaltyLandingActivity.localViewModelCompositionLocal.current
) {
    val data = viewModel.getDummyRewardDetailResponse()
    Box(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = ScrollState(0))
                .padding(top = 14.dp, bottom = 96.dp, start = 20.dp, end = 20.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                data.title?.let { TextWithPlaceholders(modifier = Modifier.weight(1f), textModel = it) }
                Box(modifier = Modifier.size(70.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_aob_coworking_unselected),
                        contentDescription = null, contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 13.dp)
                    .padding(horizontal = 12.dp)
            ) {
                data.benefits?.list?.let {
                    it.forEach { text: TextModel ->
                        if ("bullet" == (text.itemType ?: "")) {
                            Row(verticalAlignment = Alignment.Top) {
                                Text("\u2022", color = getComposeColor(text.color))
                                Spacer(modifier = Modifier.width(8.dp))
                                TextWithPlaceholders(text)
                            }
                        } else {
                            TextWithPlaceholders(text)
                        }
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }

            data.steps?.let {
                Spacer(Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = Color(0xffe8e8e8), shape = RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    it.title?.let { it1 -> TextWithPlaceholders(textModel = it1) }
                    it.list?.let { steps ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        ) {
                            steps.forEach { step ->
                                Column(modifier = Modifier.weight(1f)) {
                                    Image(painter = getComposeImageFromUrl(step.imgUrl), contentDescription = null)
                                    TextWithPlaceholders(textModel = step)
                                }
                            }
                        }
                    }
                }
            }

            data.pointprogress?.let {
                Spacer(Modifier.height(12.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = Color(0xffe8e8e8), shape = RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    it.title?.let { it1 -> TextWithPlaceholders(textModel = it1) }
                    Spacer(Modifier.height(12.dp))
                    it.list?.let { steps ->
                        steps.forEachIndexed { index, step ->
                            if (index > 0) {
                                Box(
                                    Modifier
                                        .height(20.dp)
                                        .padding(start = 6.dp)
                                        .width(2.dp)
                                        .background(color = Color(0xffe8e8e8))
                                )
                            }
                            Row(Modifier.fillMaxWidth()) {
                                Box(
                                    Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                        .border(width = 1.dp, color = Color(0xffe8e8e8), shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("\u2022", color = Color(0xffe8e8e8))
                                }
                                Spacer(Modifier.width(13.dp))
                                step.title?.let { it1 -> TextWithPlaceholders(textModel = it1) }
                                Spacer(Modifier.weight(1f))
                                Row(
                                    Modifier
                                        .background(color = Color(0xfffff7e1), shape = RoundedCornerShape(4.dp))
                                        .padding(horizontal = 4.dp)
                                ) {
                                    step.earnedPoints?.let {
                                        Text(
                                            "Earned",
                                            fontSize = 12.sp,
                                            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                                            color = textColorDark
                                        )
                                        Spacer(Modifier.width(3.dp))
                                        Image(
                                            painter = painterResource(R.drawable.loyalty_coin),
                                            contentDescription = null,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(Modifier.width(3.dp))
                                        Text(
                                            step.earnedPoints,
                                            fontSize = 12.sp,
                                            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                                            color = textColorDark
                                        )
                                    }
                                    step.tobeearned?.let {
                                        Image(
                                            painter = painterResource(R.drawable.loyalty_coin),
                                            contentDescription = null,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(Modifier.width(3.dp))
                                        Text(
                                            step.tobeearned,
                                            fontSize = 12.sp,
                                            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                                            color = textColorDark
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        Box(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(28.dp) // Thin layer for shadow
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0x22000000)) // Shadow gradient
                        ), shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .background(color = Color(0xfffcfcfc), shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .background(color = mbRed, shape = RoundedCornerShape(50))
                    .padding(vertical = 11.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Shortlist Projects",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                )
            }
        }
    }
}
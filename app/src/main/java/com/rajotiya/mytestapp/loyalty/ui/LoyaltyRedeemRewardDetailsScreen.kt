package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.loyalty.LoyaltyLandingActivity
import com.rajotiya.mytestapp.loyalty.LoyaltyUserEvents
import com.rajotiya.mytestapp.loyalty.LoyaltyViewModel
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont
import com.rajotiya.mytestapp.utility.getFontFamily

@Composable
fun LoyaltyRedeemRewardDetailsScreen(
    modifier: Modifier,
    viewModel: LoyaltyViewModel = LoyaltyLandingActivity.localViewModelCompositionLocal.current
) {
    Box {
        Column(
            modifier = modifier
                .background(color = Color.White),
        ) {
            LoyaltyHeader(modifier = Modifier.fillMaxWidth(), points = "1500") {
                viewModel.sendUserEvent(LoyaltyUserEvents.BackBtnClicked)
            }
            MainView(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Box(
                modifier = Modifier.align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(28.dp) // Thin layer for shadow
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent,Color(0x22000000)) // Shadow gradient
                        ), shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
            )
            Box(
                Modifier
                    .fillMaxWidth().padding(top = 8.dp)
                    .background(color = Color(0xfffcfcfc), shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .background(color = mbRed, shape = RoundedCornerShape(50))
                    .padding(vertical = 11.dp),
                contentAlignment = Alignment.Center
            ) {
                LoyaltyPointsView(
                    textBeforeIcon = "Unlock for",
                    textAfterIcon = "10,000 points",
                    beforeStyle = TextStyle(
                        fontSize = 18.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR), color = Color.White
                    ),
                    afterStyle = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                        color = Color.White
                    ),
                    iconSize = 18
                )
            }
        }
    }
}

@Composable
private fun MainView(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoyaltyRewardBrief(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        AboutLoyaltyReward(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        LoyaltyHowToUseView(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        LoyaltyFAQView(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        LoyaltyTermsConditionsView(
            modifier = Modifier,
            tnc = "These are the terms and Conditions. These are the terms and Conditions. These are the terms and Conditions. These are the terms and Conditions. These are the terms and Conditions. These are the terms and Conditions. "
        )
        Spacer(modifier = Modifier.height(12.dp))

    }

}

@Composable
private fun BottomView(modifier: Modifier = Modifier, onRedeem: () -> Unit) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 23.dp, topEnd = 23.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        BottomRedButton(enable = true, ctaText = "Unlock for", onClick = onRedeem)
    }

}

@Composable
private fun BottomRedButton(
    modifier: Modifier = Modifier,
    enable: Boolean,
    ctaText: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { if (enable) onClick() }
            .background(color = Color(0xffd8232a), shape = RoundedCornerShape(50))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ctaText,
                color = if (enable) Color.White else Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))

            )
            Image(
                painter = painterResource(id = R.drawable.loyalty_coin), contentDescription = null,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "1000",
                color = if (enable) Color.White else Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD))
            )
        }
    }
}

@Preview
@Composable
fun LoyaltyRedeemRewardDetailsScreenPreview() {
    LoyaltyRedeemRewardDetailsScreen(modifier = Modifier.fillMaxSize())
}
package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.loyalty.LoyaltyLandingActivity
import com.rajotiya.mytestapp.loyalty.LoyaltySectionConstants
import com.rajotiya.mytestapp.loyalty.LoyaltyUserEvents
import com.rajotiya.mytestapp.loyalty.LoyaltyViewModel
import com.rajotiya.mytestapp.loyalty.models.RewardDetailData
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.LoaderUI
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.showErrorMessageToast

@Composable
fun LoyaltyRedeemRewardDetailsScreen(
    modifier: Modifier,
    viewModel: LoyaltyViewModel = LoyaltyLandingActivity.localViewModelCompositionLocal.current
) {
    val rewardDetailResponse by viewModel.rewardDetails.collectAsState()
    when (val response = rewardDetailResponse.peekContent()) {
        is MBCoreResultEvent.OnFailure -> {
            showErrorMessageToast(LocalContext.current, "Some error occurred!")
        }

        MBCoreResultEvent.OnLoading -> {
            LoaderUI(modifier)
        }

        is MBCoreResultEvent.OnSuccess -> {
            RewardDetailUI(modifier = modifier, data = response.data, viewModel = viewModel)
        }

    }


}

@Composable
private fun RewardDetailUI(modifier: Modifier = Modifier, data: RewardDetailData, viewModel: LoyaltyViewModel) {
    Box {
        Column(
            modifier = modifier
                .background(color = Color.White),
        ) {
            LoyaltyHeader(modifier = Modifier.fillMaxWidth(), points = data.earnedPoints) {
                viewModel.sendUserEvent(LoyaltyUserEvents.BackBtnClicked)
            }
            MainView(modifier = Modifier.fillMaxWidth(), data)
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
                LoyaltyPointsView(
                    textBeforeIcon = "Unlock for",
                    textAfterIcon = "${data.points} points",
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
private fun MainView(modifier: Modifier, data: RewardDetailData) {
    LazyColumn(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 95.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            LoyaltyRewardBrief(
                modifier = Modifier.fillMaxWidth(),
                title = data.title,
                titleUrl = data.titleUrl,
                worth = data.worth,
                points = data.points
            )
        }
//        Spacer(modifier = Modifier.height(12.dp))
        data.items?.let {
            items(it) { item ->
                when (item.type) {
                    LoyaltySectionConstants.AboutThisReward -> {
                        AboutLoyaltyReward(modifier = Modifier.fillMaxWidth(), item)
                    }

                    LoyaltySectionConstants.HowToUseReward -> {
                        LoyaltyHowToUseView(modifier = Modifier.fillMaxWidth(), item)
                    }

                    LoyaltySectionConstants.Faq -> {
                        LoyaltyFAQView(modifier = Modifier.fillMaxWidth(), item)
                    }

                    LoyaltySectionConstants.TnC -> {
                        LoyaltyTermsConditionsView(modifier = Modifier.fillMaxWidth(), item)
                    }
                }
            }
        }

    }

}
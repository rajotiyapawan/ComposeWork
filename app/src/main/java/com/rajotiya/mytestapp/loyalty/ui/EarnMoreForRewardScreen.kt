package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rajotiya.mytestapp.loyalty.LoyaltyLandingActivity
import com.rajotiya.mytestapp.loyalty.LoyaltySectionConstants
import com.rajotiya.mytestapp.loyalty.LoyaltyUserEvents
import com.rajotiya.mytestapp.loyalty.LoyaltyViewModel
import com.rajotiya.mytestapp.loyalty.models.RewardDetailData
import com.rajotiya.mytestapp.utility.MBCoreResultEvent

@Composable
fun EarnMoreForRewardUI(
    modifier: Modifier = Modifier,
    viewModel: LoyaltyViewModel = LoyaltyLandingActivity.localViewModelCompositionLocal.current
) {
    val rewardDetailResponse by viewModel.rewardDetails.collectAsState()
    when (val response = rewardDetailResponse.peekContent()) {
        is MBCoreResultEvent.OnSuccess -> {
            RewardDetailUI(modifier = modifier, data = response.data, viewModel = viewModel)
        }

        else -> {
            viewModel.sendUserEvent(LoyaltyUserEvents.BackBtnClicked)
        }
    }
}

@Composable
private fun RewardDetailUI(modifier: Modifier = Modifier, data: RewardDetailData, viewModel: LoyaltyViewModel) {
    Column(
        modifier = modifier
            .background(color = Color.White),
    ) {
        LoyaltyHeader(modifier = Modifier.fillMaxWidth(), points = data.earnedPoints) {
            viewModel.sendUserEvent(LoyaltyUserEvents.BackBtnClicked)
        }
        MainView(modifier = Modifier.fillMaxWidth(), data)
    }
}


@Composable
private fun MainView(modifier: Modifier, data: RewardDetailData) {
    LazyColumn(
        modifier = modifier
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            LoyaltyRewardBrief(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                title = data.title,
                titleUrl = data.titleUrl,
                worth = data.worth,
                points = data.points
            )
        }
        item{
            HowToEarnPoints()
        }
        data.items?.let {
            items(it) { item ->
                when (item.type) {
                    LoyaltySectionConstants.AboutThisReward -> {
                        AboutLoyaltyReward(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), item)
                    }

                    LoyaltySectionConstants.HowToUseReward -> {
                        LoyaltyHowToUseView(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), item)
                    }

                    LoyaltySectionConstants.Faq -> {
                        LoyaltyFAQView(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), item)
                    }

                    LoyaltySectionConstants.TnC -> {
                        LoyaltyTermsConditionsView(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), item)
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.height(25.dp)) }
    }

}
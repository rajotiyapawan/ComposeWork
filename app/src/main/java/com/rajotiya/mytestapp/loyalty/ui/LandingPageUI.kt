package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.loyalty.LoyaltyLandingActivity
import com.rajotiya.mytestapp.loyalty.LoyaltyScreens
import com.rajotiya.mytestapp.loyalty.LoyaltyUserEvents
import com.rajotiya.mytestapp.loyalty.LoyaltyViewModel
import com.rajotiya.mytestapp.loyalty.models.LoyaltyLandingPageData
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.LoaderUI
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import com.rajotiya.mytestapp.utility.getFont
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.mbToast
import com.rajotiya.mytestapp.utility.showErrorMessageToast

@Composable
fun LandingPageUI(
    modifier: Modifier = Modifier,
    viewModel: LoyaltyViewModel = LoyaltyLandingActivity.localViewModelCompositionLocal.current
) {
    val apiResponse by viewModel.landingPageData.collectAsState()
    var uiData by remember { mutableStateOf<LoyaltyLandingPageData?>(null) }
    when (val response = apiResponse.getContentIfNotHandled()) {
        is MBCoreResultEvent.OnFailure -> {
            mbToast(LocalContext.current, "Some error occurred!")
        }

        MBCoreResultEvent.OnLoading -> {
            LoaderUI(modifier)
        }

        is MBCoreResultEvent.OnSuccess -> {
            uiData = response.data
        }

        null -> {}
    }

    uiData?.let { LoadData(modifier = modifier, it, viewModel) }

    val rewardDetailResponse by viewModel.rewardDetails.collectAsState()
    when (rewardDetailResponse.getContentIfNotHandled()) {
        is MBCoreResultEvent.OnFailure -> {
            showErrorMessageToast(LocalContext.current, "Some error occurred!")
        }

        MBCoreResultEvent.OnLoading -> {
            LoaderUI(modifier)
        }

        is MBCoreResultEvent.OnSuccess -> {
            viewModel.sendUserEvent(LoyaltyUserEvents.NavigateTo(route = LoyaltyScreens.EarnMoreForReward.name))
            LoaderUI(modifier)
        }

        null -> {}
    }
}

@Composable
private fun LoadData(modifier: Modifier, data: LoyaltyLandingPageData, viewModel: LoyaltyViewModel) {
    var isCollapseVedioPlayerVisible by remember { mutableStateOf(true) }
    Box {
        LazyColumn(modifier = modifier.background(color = Color(0xfff5f5f5))) {
            item {
                LandingPageTopSection(modifier = Modifier.fillMaxWidth())
            }
            item {
                LandingPageRewardSection(modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp), onClaim = {
                    viewModel.getRewardDetails(it)
                })
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp, start = 16.dp, end = 20.dp, bottom = 10.dp),
                    text = "FAQs",
                    color = Color.Black,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    fontSize = 18.sp
                )
            }
            items(5) {
                LandingPageFaq(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 20.dp, bottom = 12.dp)
                )
            }
        }
        if (isCollapseVedioPlayerVisible) {
            LoyalityVideoPlayer(modifier = modifier, false) {
                isCollapseVedioPlayerVisible = false
            }
        }
    }
}

@Composable
private fun LandingPageTopSection(modifier: Modifier = Modifier) {
    val bgImage = ImageBitmap.imageResource(R.drawable.loyalty_landing_bg)
    Column(modifier = modifier
        .drawBehind {
            drawImage(
                image = bgImage,
                dstSize = IntSize(size.width.toInt(), size.height.toInt())
            )
        }
        .padding(bottom = 50.dp)
    ) {
        HeaderView(modifier = Modifier.fillMaxWidth())
        TabsView(modifier = Modifier.fillMaxWidth())
        if (false) { // check if it is first visit to landing page else do not show
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp)
            ) {
                LoyalityVideoPlayer(modifier = Modifier.fillMaxWidth(), true) {

                }
            }
        }
        TopReward(
            modifier = Modifier
                .padding(vertical = 28.dp, horizontal = 22.dp)
                .height(140.dp)
        )
        MilestoneView(
            Modifier.fillMaxWidth(),
            milestones = listOf("0", "1K", "2k", "10K", "12K"),
            currentPoints = 1500,
            progressLineHeight = 12
        )
        HowUI(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        )
    }
}

@Composable
private fun LandingPageRewardSection(modifier: Modifier = Modifier, onClaim: (id: String) -> Unit) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
        ) {
            Text(
                "Claim Exclusive Rewards",
                fontSize = 18.sp,
                color = textColorDark,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            Spacer(modifier = Modifier.height(16.dp))
            val state = rememberLazyGridState()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 1500.dp),
                state = state,
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(13) {
                    ExclusiveRewardItemView(modifier = Modifier, onClaim = onClaim)
                }
            }
        }
    }
}

@Composable
private fun HeaderView(modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 20.dp), verticalAlignment = Alignment.Top) {
        Image(painter = painterResource(R.drawable.yellow_diamond_outlined), contentDescription = null)
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                "MB Elite Club",
                fontSize = 16.sp,
                letterSpacing = (-0.3).sp,
                color = Color(0xfff1bd65),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            Text(
                "Rewards for your property journey!",
                fontSize = 11.sp,
                color = Color.White,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier
                .padding(end = 17.dp, bottom = 10.dp)
                .background(Color(0x2effffff), shape = RoundedCornerShape(100.dp))
                .padding(horizontal = 4.dp, vertical = 2.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.loyalty_coin), contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = "1500",
                color = Color(0xffe8b321),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun TabsView(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = Color(0x19f1bd65))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "What is Elite Club?",
            fontSize = 12.sp,
            lineHeight = 20.sp,
            color = Color(0xfff5f5f5),
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            modifier = Modifier
                .background(color = Color.Black, shape = RoundedCornerShape(100.dp))
                .padding(horizontal = 8.dp)
        )
        Text(
            "My Rewards",
            fontSize = 12.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
            color = Color(0xfff5f5f5)
        )
        Text("FAQ", fontSize = 12.sp, fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)), color = Color(0xfff5f5f5))
    }
}

@Composable
private fun TopReward(modifier: Modifier = Modifier) {
    Box(modifier = modifier)
}

@Composable
private fun HowUI(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xffe8d5c5), shape = RoundedCornerShape(50))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xff590250),
                            Color(0xffd8232a)
                        )
                    ), shape = RoundedCornerShape(50)
                )
                .padding(
                    top = 5.dp, bottom = 7.dp, start = 8.dp, end = 8
                        .dp
                ), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
            Text("How?", fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)), fontSize = 14.sp, color = Color.White)
        }
        Row(
            modifier = Modifier
                .background(color = Color(0x26ffffff), shape = RoundedCornerShape(50))
                .padding(vertical = 11.dp, horizontal = 24.dp)
        ) {
            Text("Earn Points", fontSize = 14.sp, color = Color.White, fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))
            Icon(
                Icons.AutoMirrored.Outlined.ArrowForward, contentDescription = null, modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .size(width = 16.dp, height = 17.dp),
                tint = Color(0xff909090)
            )
            Text(
                "Claim Rewards!",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
            )
        }
    }
}

@Composable
private fun LandingPageFaq(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(
                color = Color.White, shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "What is MB Elite Club?",
            color = textColorDark,
            fontSize = 14.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
        )
        Icon(Icons.Outlined.AddCircle, contentDescription = null)
    }
}
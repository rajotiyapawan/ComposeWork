package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont

@Composable
fun EarnMoreForRewardUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = Color.Black)
            .verticalScroll(state = ScrollState(initial = 0))
    ) {
        LoyaltyHeader(modifier = Modifier.fillMaxWidth(), points = "1500") {}
        RewardUI(
            modifier = Modifier
                .fillMaxWidth()
                .height(342.dp)
        )
        DetailsUI(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-21).dp)
                .padding(horizontal = 7.dp)
                .padding(bottom = 20.dp)
        )
    }
}


@Composable
private fun RewardUI(modifier: Modifier = Modifier) {
    Box(modifier.background(color = Color.Green))
}

@Composable
private fun DetailsUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(
                color = Color.White, shape = RoundedCornerShape(24.dp)
            )
            .padding(top = 21.dp, start = 9.dp, end = 9.dp, bottom = 52.dp)
    ) {
        PointsAlert(modifier = Modifier.fillMaxWidth(), reqMorePoints = "10500")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 36.dp)
                .height(1.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0x00d8232a),
                            Color(0xff460a91),
                            Color(0x00c21f3a)
                        )
                    )
                )
        )
        HowToEarnPoints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
//        AboutLoyaltyReward(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 18.dp)
//        )
//        LoyaltyHowToUseView(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 12.dp)
//        )
//        LoyaltyFAQView(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 12.dp)
//        )
//        LoyaltyTermsConditionsView(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 12.dp),
//            tnc = "These are the terms and Conditions. These are the terms and Conditions. These are the terms and Conditions. These are the terms and Conditions. These are the terms and Conditions. These are the terms and Conditions. "
//        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun PointsAlert(modifier: Modifier = Modifier, reqMorePoints: String) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Image(painter = painterResource(R.drawable.ic_aob_cross), contentDescription = null)
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(text = buildAnnotatedString {
                append("You have ")
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                        color = Color(0xffce0037)
                    )
                ) {
                    append("Insufficient Points")
                }
            }, fontSize = 16.sp, fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)), color = Color.Black)
            val requiredPoints = buildAnnotatedString {
                append("Only ")
                appendInlineContent(id = "icon", "<icon>")
                append(" $reqMorePoints more required")
            }
            val inlineContent = mapOf(
                Pair(
                    "icon", InlineTextContent(
                        placeholder = Placeholder(
                            height = 14.sp,
                            width = 14.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                        )
                    ) {
                        Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
                    }
                )
            )
            Text(
                text = requiredPoints,
                inlineContent = inlineContent,
                fontSize = 13.sp,
                lineHeight = 14.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
            )
        }
    }
}

@Composable
private fun HowToEarnPoints(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 5.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                "How to Earn",
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
                fontSize = 16.sp,
                lineHeight = 32.sp,
                color = textColorDark
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
                    .height(1.dp)
                    .background(brush = Brush.linearGradient(colors = listOf(Color(0xffd7d7d7), Color(0x00717171))))
            )
        }
        repeat(6) {
            TaskItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {}
        }
    }
}

@Composable
private fun TaskItem(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier = modifier) {
        val earnText = buildAnnotatedString {
            append("Earn ")
            appendInlineContent("icon", "<icon>")
            withStyle(style = SpanStyle(fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD)), fontSize = 18.sp)) {
                append(" 500")
            }
        }
        val inlineTextContent = mapOf(
            Pair("icon", InlineTextContent(
                placeholder = Placeholder(
                    width = 18.sp,
                    height = 18.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) { Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null) })
        )
        Card(
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.BottomStart),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            border = BorderStroke(width = 1.dp, color = Color(0xffe8e8e8)),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 7.dp, top = 19.dp, bottom = 6.dp)
            ) {
                Text(
                    "Verify yourself on the Magicbricks app",
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    "Also get Verified Badge",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                    color = textColorDark
                )
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Text("Download App",
                        fontSize = 14.sp,
                        color = Color.White,
                        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_BOLD)),
                        modifier = Modifier
                            .noRippleClick { onClick() }
                            .background(color = mbRed, shape = RoundedCornerShape(50))
                            .padding(vertical = 6.dp, horizontal = 20.dp))
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 8.dp, end = 5.dp)
                .background(
                    brush = Brush.linearGradient(colors = listOf(Color(0xffFFEBB3), Color.White)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(start = 7.dp, top = 2.dp, bottom = 2.dp)
        ) {
            Text(
                earnText,
                inlineContent = inlineTextContent,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                fontSize = 14.sp,
                color = textColorDark,
            )
        }
    }
}
package com.rajotiya.mytestapp.sitevisit_flow.ui

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily

@Composable
fun LoyaltySvWhatYouGet(modifier: Modifier = Modifier, withBackground: Boolean = true) {
    val modifier2 = if (withBackground) modifier
        .border(width = 1.dp, brush = Brush.linearGradient(colors = listOf(Color(0xff80cbc0), Color(0xffe0f2f0))), shape = RoundedCornerShape(8.dp))
        .background(brush = Brush.linearGradient(colors = listOf(Color(0xffffffff), Color(0xffedfaf9))), shape = RoundedCornerShape(8.dp))
        .padding(start = 10.dp, top = 12.dp, end = 16.dp, bottom = 13.dp)
    else modifier
    Column(
        modifier = modifier2
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("WHAT YOU GET", color = textColorDark, fontSize = 12.sp, lineHeight = 24.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))
            Spacer(Modifier.width(14.dp))
            Box(
                Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(color = Color(0xffb2dfd8))
            )
        }
        Spacer(Modifier.height(4.dp))
        val pointsList = listOf(
            "A dedicated cab & driver for your entire trip",
            "Guaranteed gift vouchers worth `3,000"
        )
        pointsList.forEach { item -> PointsView(item) }
        Spacer(Modifier.height(8.dp))
        Box {
            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .background(color = Color(0xfffaf7f0), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RewardView(Modifier.weight(1f))
                RewardView(Modifier.weight(1f))
            }
            Row(
                Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 7.dp)
                    .border(width = 1.dp, color = Color(0xfffdeec8), shape = RoundedCornerShape(4.dp))
                    .background(color = Color(0xfffff7e1), shape = RoundedCornerShape(4.dp))
                    .padding(vertical = 3.dp, horizontal = 2.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(modifier = Modifier.size(10.dp), painter = painterResource(R.drawable.yellow_diamond_outlined), contentDescription = null)
                Spacer(Modifier.width(2.dp))
                Text("Elite Club", fontSize = 10.sp, color = Color(0xffab7c00), fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))
            }
        }
        Text(
            "& many more", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End, fontSize = 11.sp, lineHeight = 14.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR), color =
                textColorLight
        )
    }
}

@Composable
fun LoyaltySvSuccessView(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(buildAnnotatedString {
            append("Complete the site visit to redeem your points for Gift Vouchers worth `3,000.")
            append(" ")
            withStyle(style = SpanStyle(color = mbRed, textDecoration = TextDecoration.Underline)){
                append("Know more")
            }
        }, fontSize = 12.sp, lineHeight = 18.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR), color = textColorLight)
        Row(
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .background(color = Color(0xfffaf7f0), shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 6.dp, vertical = 8.dp)
        ) {
            RewardView(Modifier.weight(1f))
            RewardView(Modifier.weight(1f))
        }
        Text(
            "& many more", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End, fontSize = 11.sp, lineHeight = 14.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR), color =
                textColorLight
        )
    }
}

@Composable
private fun RewardView(modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(20.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(4.dp))
        )
        Spacer(Modifier.width(4.dp))
        Text("Amazon Gift Card\nworth `2,000", fontSize = 11.sp, lineHeight = 14.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM), color = textColorDark)
    }
}

@Composable
private fun PointsView(text: String) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.Check, tint = Color(0xff009681),
                contentDescription = null, modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = text,
                color = textColorDark,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                fontSize = 14.sp, lineHeight = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview
@Composable
fun TestLoyaltySvView() {
    Column(
        Modifier
            .background(color = Color.White)
            .padding(12.dp)
    ) {
        LoyaltySvWhatYouGet()
        Spacer(Modifier.height(16.dp))
        LoyaltySvSuccessView(Modifier.padding(start = 120.dp))
    }
}
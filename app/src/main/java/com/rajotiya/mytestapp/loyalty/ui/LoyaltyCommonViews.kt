package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFont
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 24-12-2024.
 */

@Composable
fun LoyaltyHeader(modifier: Modifier, points: String, onBack: () -> Unit) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(vertical = 16.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = null,
                tint = Color(0xff606060),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .size(24.dp)
                    .noRippleClick { onBack() }
            )
            LoyaltyPointsView(
                modifier = Modifier,
                textBeforeIcon = "Your Points",
                textAfterIcon = points,
                iconSize = 12,
                beforeStyle = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                    color = Color.Black
                ),
                afterStyle = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    color = Color.Black
                )
            )
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0xffd7d7d7), thickness = 1.dp)
    }
}

@Composable
fun LoyaltyPointsView(
    modifier: Modifier = Modifier,
    textBeforeIcon: String,
    beforeStyle: TextStyle,
    textAfterIcon: String,
    afterStyle: TextStyle,
    iconSize: Int
) {
    Row(modifier = modifier) {
        Text(
            text = textBeforeIcon, style = beforeStyle
        )
        Image(
            painter = painterResource(id = R.drawable.loyalty_coin), contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(iconSize.dp)
        )
        Text(
            text = textAfterIcon, style = afterStyle
        )
    }
}

@Composable
fun LoyaltyTermsConditionsView(modifier: Modifier, tnc: String) {
    var expandedState by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Terms and conditions", fontSize = 16.sp,
                color = Color(0xff303030),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            IconButton(
                onClick = {
                    expandedState = !expandedState
                }
            ) {
                Icon(
                    imageVector = if (expandedState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xff909090)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp), color = Color(0xffd9d9d9), thickness = 1.dp
        )
        if (expandedState) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tnc,
                fontSize = 14.sp,
                color = Color(0xff606060),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
            )
        }
    }
}

@Composable
fun LoyaltyHowToUseView(modifier: Modifier) {
    var expandedState by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "How to use",
                fontSize = 16.sp,
                color = Color(0xff303030),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            IconButton(
                onClick = {
                    expandedState = !expandedState
                },
            ) {
                Icon(
                    imageVector = if (expandedState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xff909090)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp), color = Color(0xffd9d9d9), thickness = 1.dp
        )
        if (expandedState) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    "After you redeem this reward: \n",
                    fontSize = 12.sp,
                    color = Color(0xff000000),
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))

                )
                BulletedTextView("Earn more points")
                BulletedTextView("Earn more points")
                BulletedTextView("Earn more points")
                BulletedTextView("Go to the payment options and select the Gift Voucher payment method.")
                BulletedTextView("Earn more points")

            }
        }
    }
}

@Composable
fun LoyaltyFAQView(modifier: Modifier) {
    var expandedState by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Frequently asked questions",
                fontSize = 16.sp,
                color = Color(0xff303030),
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            IconButton(
                onClick = {
                    expandedState = !expandedState
                },
            ) {
                Icon(
                    imageVector = if (expandedState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xff909090)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp), color = Color(0xffd9d9d9), thickness = 1.dp
        )
        if (expandedState) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 1500.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(5) {
                    FaqItem(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
private fun FaqItem(modifier: Modifier = Modifier) {
    var expandedState by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .border(width = 1.dp, color = Color(0xffe8e8e8), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp, bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "What is MB Elite Club?",
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                fontSize = 14.sp,
                color = textColorDark
            )
            IconButton(
                onClick = {
                    expandedState = !expandedState
                },
            ) {
                Icon(
                    imageVector = if (expandedState) Icons.Default.AddCircle else Icons.Default.AddCircle,
                    contentDescription = null
                )
            }
        }
        if (expandedState) {
            Text(
                "Earn points by exploring the Magicbricks App or utilizing our newly launched Site Visit, Home Loan & Home Interior Services.",
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                fontSize = 14.sp,
                color = textColorDark
            )
        }
    }
}

@Composable
fun AboutLoyaltyReward(modifier: Modifier) {
    Column(
        modifier = modifier
            .background(color = Color(0xfffff5cc), shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Text(
            "About this Reward",
            fontSize = 18.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)),
            color = Color(0xff303030)
        )
        Spacer(modifier = Modifier.height(12.dp))
        BulletedTextView("Get a BookMyShow Gift Voucher worth ₹500.")
        BulletedTextView("Get a BookMyShow Gift Voucher worth ₹500.")
        BulletedTextView("This reward cannot be sold, canceled, returned, refunded, or exchanged once claimed.")

    }
}

@Composable
private fun BulletedTextView(text: String) {

    Row {
        Text(
            " • ",
            fontSize = 12.sp,
            color = Color(0xff000000),
            lineHeight = 18.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
        )
        Text(
            text,
            fontSize = 12.sp,
            color = Color(0xff000000),
            lineHeight = 18.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
        )
    }
}

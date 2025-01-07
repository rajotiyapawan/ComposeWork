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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.loyalty.models.FaqItem
import com.rajotiya.mytestapp.loyalty.models.LoyaltySections
import com.rajotiya.mytestapp.loyalty.models.TextModel
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.TextWithPlaceholders
import com.rajotiya.mytestapp.utility.getComposeColor
import com.rajotiya.mytestapp.utility.getComposeFont
import com.rajotiya.mytestapp.utility.getFont
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 24-12-2024.
 */

@Composable
fun LoyaltyHeader(modifier: Modifier, points: String?, onBack: () -> Unit) {
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
                    .size(16.dp)
                    .noRippleClick { onBack() }
            )
            LoyaltyPointsView(
                modifier = Modifier,
                textBeforeIcon = "Your Points",
                textAfterIcon = points ?: "",
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
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
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
fun LoyaltyTermsConditionsView(modifier: Modifier, item: LoyaltySections) {
    var expandedState by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClick { expandedState = !expandedState },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                item.text ?: "",
                fontSize = (item.size?.toFloat() ?: 18f).sp,
                fontFamily = getComposeFont(item.font ?: "", item.weight ?: ""),
                color = getComposeColor(item.color ?: "#303030")
            )
            Icon(
                imageVector = if (expandedState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color(0xff909090)
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp), color = Color(0xffd9d9d9), thickness = 1.dp
        )
        if (expandedState) {
            Spacer(modifier = Modifier.height(8.dp))
            item.items?.let {
                if (it.isNotEmpty()) {
                    TextWithPlaceholders(it[0])
                }
            }
        }
    }
}


@Composable
fun AboutLoyaltyReward(modifier: Modifier, item: LoyaltySections) {
    Column(
        modifier = modifier
            .background(color = Color(0xfffff5cc), shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Text(
            item.text ?: "",
            fontSize = (item.size?.toFloat() ?: 18f).sp,
            fontFamily = getComposeFont(item.font ?: "", item.weight ?: ""),
            color = getComposeColor(item.color ?: "#303030")
        )
        Spacer(modifier = Modifier.height(12.dp))
        item.items?.let {
            it.forEach { text: TextModel ->
                if ("bullet" == (text.itemtype ?: "")) {
                    Row(verticalAlignment = Alignment.Top) {
                        Text("\u2022", color = getComposeColor(text.color))
                        Spacer(modifier = Modifier.width(8.dp))
                        TextWithPlaceholders(text)
                    }
                } else {
                    TextWithPlaceholders(text)
                }
            }
        }
    }
}

@Composable
fun LoyaltyHowToUseView(modifier: Modifier, item: LoyaltySections) {
    var expandedState by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClick { expandedState = !expandedState },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                item.text ?: "",
                fontSize = (item.size?.toFloat() ?: 16f).sp,
                fontFamily = getComposeFont(item.font ?: "", item.weight ?: ""),
                color = getComposeColor(item.color ?: "#303030")
            )

            Icon(
                imageVector = if (expandedState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color(0xff909090)
            )

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
                item.subtext?.let {
                    it.forEach { text: TextModel ->
                        if ("bullet" == (text.itemtype ?: "")) {
                            Row(verticalAlignment = Alignment.Top) {
                                Text("\u2022", color = getComposeColor(text.color))
                                Spacer(modifier = Modifier.width(8.dp))
                                TextWithPlaceholders(text)
                            }
                        } else {
                            TextWithPlaceholders(text)
                        }
                    }
                }
                item.items?.let {
                    it.forEach { text: TextModel ->
                        if ("bullet" == (text.itemtype ?: "")) {
                            Row(verticalAlignment = Alignment.Top) {
                                Text("\u2022", color = getComposeColor(text.color))
                                Spacer(modifier = Modifier.width(8.dp))
                                TextWithPlaceholders(text)
                            }
                        } else {
                            TextWithPlaceholders(text)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoyaltyFAQView(modifier: Modifier, item: LoyaltySections) {
    var expandedState by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClick { expandedState = !expandedState },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                item.text ?: "",
                fontSize = (item.size?.toFloat() ?: 16f).sp,
                fontFamily = getComposeFont(item.font ?: "", item.weight ?: ""),
                color = getComposeColor(item.color ?: "#303030")
            )
            Icon(
                imageVector = if (expandedState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color(0xff909090)
            )
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
                item.qna?.let {
                    items(it) { faqItem: FaqItem ->
                        FaqItem(modifier = Modifier.fillMaxWidth(), faqItem)
                    }
                }
            }
        }
    }
}

@Composable
private fun FaqItem(modifier: Modifier = Modifier, faqItem: FaqItem) {
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
                .noRippleClick { expandedState = !expandedState }
                .padding(bottom = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            faqItem.que?.let { TextWithPlaceholders(it) }
            Icon(
                imageVector = if (expandedState) Icons.Default.AddCircle else Icons.Sharp.AddCircle,
                contentDescription = null, modifier = Modifier.size(20.dp)
            )
        }
        if (expandedState) {
            faqItem.ans?.let { TextWithPlaceholders(it) }
        }
    }
}

@Composable
fun LoyaltyRewardBrief(modifier: Modifier = Modifier, title: String?, titleUrl: String?, worth: String?, points: String?) {
    Row(
        modifier = modifier
            .padding(end = 20.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_prime_bitmap_chat),
            contentDescription = null,
            modifier = Modifier
                .width(79.dp)
                .height(75.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray)
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp, top = 2.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = title ?: "",
                color = Color(0xff000000),
                fontSize = 16.sp, lineHeight = 18.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD))
            )
            WorthView(price = worth)
            Spacer(modifier = Modifier.height(12.dp))
            LoyaltyPointsView(
                modifier = Modifier,
                textBeforeIcon = "Used",
                textAfterIcon = "$points points",
                iconSize = 14,
                beforeStyle = TextStyle(
                    fontSize = 12.sp,
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
    }
}

@Composable
fun AlreadyClaimedView() {
    Column {
        Row(
            modifier = Modifier
                .background(color = Color(0xffe0f2f0), shape = RoundedCornerShape(4.dp))
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Check, contentDescription = null, tint = Color(0xff009681),
                modifier = Modifier.width(8.dp)
            )
            Text(
                text = "Already Claimed",
                color = Color(0xff009681),
                fontSize = 13.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Text(
            text = "Voucher Code has  been shared to your registered email id.",
            color = Color(0xff303030),
            fontSize = 14.sp,
            fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR)),
            modifier = Modifier.padding(top = 8.dp, bottom = 13.dp)
        )
    }
}

@Composable
private fun WorthView(price: String?) {
    Box(
        Modifier
            .background(color = Color(0xfffff7e1), shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 5.dp, vertical = 1.dp)
    ) {
        Text(text = buildAnnotatedString {
            append("Worth ")
            withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_BOLD))) {
                append("₹ $price")
            }
        }, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR), color = Color.Black)
    }
}
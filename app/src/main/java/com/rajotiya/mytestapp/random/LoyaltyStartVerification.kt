package com.rajotiya.mytestapp.random

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily

@Composable
fun TestVerificationDialogUI() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 24.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, top = 24.dp, end = 18.dp, bottom = 13.dp)
            ) {
                Text(
                    "4 Benefits",
                    color = textColorLight,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                )
                Text(
                    "Ready to be unlocked",
                    color = Color(0xff009681),
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                )
                Spacer(Modifier.height(12.dp))
                Column(
                    Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            brush = Brush.verticalGradient(colors = listOf(Color(0xffe8e8e8), Color(0x00ffffff))),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            brush = Brush.verticalGradient(colors = listOf(Color(0xfff7f7f7), Color(0x66ffffff))),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(top = 8.dp, bottom = 8.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(R.drawable.ic_prime_bitmap_chat), contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            buildAnnotatedString {
                                append("Phone numbers of 30 owners for\n")
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                ) {
                                    append("\u20b9 1,899 ")
                                }
                                append(" ")
                                appendInlineContent("freeTag")
                            },
                            fontSize = 14.sp,
                            inlineContent = mapOf(
                                "freeTag" to InlineTextContent(
                                    placeholder = Placeholder(
                                        width = 40.sp,
                                        height = 20.sp,
                                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                                    )
                                ) {
                                    Text(
                                        "Free",
                                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        color = textColorDark,
                                        modifier = Modifier
                                            .background(
                                                color = Color(0xffffde82),
                                                shape = RoundedCornerShape(4.dp)
                                            )
                                            .padding(horizontal = 3.dp)
                                    )
                                }
                            ),
                            lineHeight = 20.sp,
                            color = textColorLight,
                            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM)
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            Modifier
                                .weight(1f)
                                .padding(horizontal = 6.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.Done, contentDescription = null)
                            Spacer(Modifier.height(4.dp))
                            Text(
                                "Verified Badge",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                                color = textColorLight,
                                textAlign = TextAlign.Center
                            )
                        }
                        Box(
                            Modifier
                                .padding(horizontal = 13.dp)
                                .size(5.dp)
                                .background(color = Color(0xffd9d9d9), shape = CircleShape)
                        )
                        Column(
                            Modifier
                                .weight(1f)
                                .padding(horizontal = 6.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.Done, contentDescription = null)
                            Spacer(Modifier.height(4.dp))
                            Text(
                                "Verified Badge",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                                color = textColorLight,
                                textAlign = TextAlign.Center
                            )
                        }
                        Box(
                            Modifier
                                .padding(horizontal = 13.dp)
                                .size(5.dp)
                                .background(color = Color(0xffd9d9d9), shape = CircleShape)
                        )
                        Column(
                            Modifier
                                .weight(1f)
                                .padding(horizontal = 6.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.Done, contentDescription = null)
                            Spacer(Modifier.height(4.dp))
                            Text(
                                "Verified Badge",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                                color = textColorLight,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 11.dp, bottom = 16.dp)
                        .height(1.dp)
                        .background(
                            brush = Brush.linearGradient(colors = listOf(Color(0x00ffe395), Color(0xffffc72c), Color(0x00ffe395)))
                        )
                )
                Text(
                    buildAnnotatedString {
                        append("Just ")
                        withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                            append("complete")
                        }
                        append(" your one-time mobile verification to ")
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xff009681),
                                fontStyle = FontStyle.Italic,
                                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                            )
                        ) {
                            append("unlock")
                        }
                    },
                    fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    color = textColorDark
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .padding(horizontal = 15.dp, vertical = 14.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(color = mbRed, shape = RoundedCornerShape(50))
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Start Now",
                        color = Color.White,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                        fontSize = 16.sp
                    )
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun LoyaltyPrimeCongratulationView(modifier: Modifier = Modifier, list: List<String>) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(153.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.prime_loyalty_ty_bg),
                    contentDescription = "Header Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_green_tick_white_star_bg),
                        contentDescription = "Tick Icon",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .weight(1f)
                                .height(1.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color(0xffb2dfd8),
                                        )
                                    )
                                )
                        )
                        Text(
                            text = "4 Benefits Unlocked",
                            color = Color.White,
                            fontFamily = getFontFamily((Constants.MONTSERRAT_SEMIBOLD)),
                            fontSize = 16.sp,
                        )
                        Box(
                            Modifier
                                .weight(1f)
                                .height(1.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xffb2dfd8),
                                            Color.Transparent,
                                        )
                                    )
                                )
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(R.drawable.ic_prime_crown), contentDescription = null)
                Spacer(Modifier.width(3.dp))
                Text(
                    buildAnnotatedString {
                        append("MB Prime ")
                        withStyle(style = SpanStyle(color = textColorLight)) {
                            append("Activated")
                        }
                    },
                    color = textColorDark,
                    fontFamily = getFontFamily((Constants.MONTSERRAT_MEDIUM)),
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "Validity: 60 days",
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                fontSize = 10.sp,
                lineHeight = 12.sp,
                color = textColorLight,
                modifier = Modifier
                    .background(color = Color(0xfff9f8f8), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 4.dp, vertical = 1.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "Now, access phone numbers of 30 owners*",
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                fontSize = 14.sp,
                lineHeight = 16.sp,
                color = textColorDark,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 17.dp, bottom = 16.dp)
                    .height(1.dp)
                    .background(
                        brush = Brush.linearGradient(colors = listOf(Color(0x00ffe395), Color(0xffffc72c), Color(0x00ffe395)))
                    )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Done, contentDescription = null)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Verified Badge",
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                        color = textColorLight,
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    Modifier
                        .padding(horizontal = 13.dp)
                        .size(5.dp)
                        .background(color = Color(0xffd9d9d9), shape = CircleShape)
                )
                Column(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Done, contentDescription = null)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "500 Reward Points",
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                        color = textColorLight,
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    Modifier
                        .padding(horizontal = 13.dp)
                        .size(5.dp)
                        .background(color = Color(0xffd9d9d9), shape = CircleShape)
                )
                Column(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Done, contentDescription = null)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Credit Report",
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                        color = textColorLight,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "*Limit of 1 phone number per day",
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                fontSize = 10.sp,
                lineHeight = 14.sp,
                color = textColorExtraLight,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(11.dp))
        }
    }
}
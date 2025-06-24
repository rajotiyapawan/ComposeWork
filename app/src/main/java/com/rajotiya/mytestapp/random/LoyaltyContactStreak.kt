package com.rajotiya.mytestapp.random

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.utility.BottomPopupDialog
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.DottedBorderBox
import com.rajotiya.mytestapp.utility.GifAsyncImage
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick
import kotlinx.coroutines.delay

/**
 * Created by Pawan Rajotiya on 17-06-2025.
 */

// kepp going and enquire alignment
// earn points daily icon width
// mbPrime white capsule tag shadow
// prime crown icon align with line


@Composable
fun LoyaltyContactStreakScreens(modifier: Modifier = Modifier) {
    val (screenInt, onChange) = remember { mutableIntStateOf(5) }
    BottomPopupDialog(modifier = Modifier.fillMaxWidth(), showDialog = true, onDismiss = {}, showCross = false) {
        when (screenInt) {
            0 -> {
                LoyaltyContactStreakPrimeIntro(modifier = modifier) { onChange(1) }
            }

            1 -> {
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                    LoyaltyContactStartBanner(modifier = modifier, isPrime = true) { onChange(2) }
                    LoyaltyContactStartBanner(modifier = modifier, isPrime = false) { onChange(2) }
                }
            }

            2 -> {
                Column {
                    LoyaltyContactEndBanner(modifier = modifier, isPrime = true) { onChange(3) }
                    LoyaltyContactEndBanner(modifier = modifier, isPrime = false) { onChange(3) }
                }
            }

            3 -> {
                Column {
                    LoyaltyContactReset1Banner(modifier = modifier,true) { onChange(4) }
                    LoyaltyContactReset1Banner(modifier = modifier,false) { onChange(4) }
                }
            }

            4 -> {
                Column {
                    LoyaltyContactCongratsMBPrimeBanner(modifier = modifier) { onChange(5) }
                    Spacer(Modifier.height(20.dp))
                    LoyaltyContactCongratsNonPrimeBanner(modifier = modifier) { onChange(5) }
                }
            }

            5 -> {
                Column {
                    LoyaltyContactReset2Banner(modifier = modifier,true) { onChange(6) }
                    LoyaltyContactReset2Banner(modifier = modifier,false) { onChange(6) }
                }
            }

            6 -> {
                LoyaltyContactStreakNonPrimeIntro(modifier = modifier) { onChange(7) }
            }

            7 -> {
                Column(Modifier.padding(bottom = 30.dp)) {
                    LoyaltyContactPrimeMegaEndBanner(modifier = modifier) { onChange(1) }
                    Spacer(Modifier.height(20.dp))
                    LoyaltyContactNonPrimeMegaEndBanner(modifier = modifier) { onChange(1) }
                }
            }
        }
    }
}

@Composable
fun LoyaltyContactStartBanner(modifier: Modifier = Modifier, isPrime: Boolean, onClose: () -> Unit) {
    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color(0xffffde82), Color(0xff99854e))),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(colors = listOf(Color(0xfffffcf2), Color(0xfffff5cc))),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(bottom = 7.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .padding(top = 16.dp, bottom = 9.dp, end = 4.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(R.drawable.kepp_going_text), contentDescription = null)
            Spacer(Modifier.width(4.dp))
            Text(
                "Enquire on more properties today.",
                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                fontSize = 12.sp,
                lineHeight = 24.sp,
                color = textColorLight
            )
        }
        if (isPrime) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                        append("5 more days")
                    }
                    append(" to unlock ")
                    appendInlineContent("mbCrown")
                    append(" MB Prime")
                },
                inlineContent = mapOf(
                    "mbCrown" to InlineTextContent(
                        placeholder = Placeholder(
                            width = 17.sp,
                            height = 20.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Top
                        )
                    ) {
                        Box(
                            Modifier
                                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                                .padding(3.dp)
                        ) {
                            Image(painter = painterResource(R.drawable.ic_prime_crown), contentDescription = null)
                        }
                    }
                ),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                color = textColorLight,
                modifier = Modifier.padding(top = 5.dp, start = 14.dp)
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            if (isPrime) {
                StreakBarPrime(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 18.dp, top = 6.dp), daysDone = 2
                )
            Text(
                "MB Prime",
                fontSize = 6.sp,
                lineHeight = 7.sp,
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                color = textColorDark,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 12.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 2.dp, vertical = 1.dp)
            )} else {
                StreakBarNonPrime( modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 18.dp, top = 6.dp),daysDone = 2, showStarSeven = true)
                Text(
                    "+50 Points",
                    fontSize = 8.sp, lineHeight = 9.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    color = textColorDark,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 10.dp)
                        .border(width = 1.dp, color = Color(0xffeb860d), shape = RoundedCornerShape(50))
                        .background(color = Color.White, shape = RoundedCornerShape(50))
                        .padding(horizontal = 2.dp, vertical = 1.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 14.dp), contentAlignment = Alignment.CenterEnd
        ) {
            Canvas(
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(18.dp)
            ) {
                drawPath(
                    path = Path().apply {
                        lineTo(size.width - size.height / 2, 0f)
                        lineTo(size.width, size.height / 2)
                        lineTo(size.width - size.height / 2, size.height)
                        lineTo(0f, size.height)
                        lineTo(size.height / 2, size.height / 2)
                        lineTo(0f, 0f)
                        close()
                    },
                    brush = Brush.linearGradient(colors = listOf(Color(0xfffff7e1), Color(0xffffeebe)))
                )
            }
            Text(
                buildAnnotatedString {
                    appendInlineContent("LoyaltyIcon")
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                        append(" Earn")
                    }
                    append("40 points each day")
                },
                inlineContent = mapOf(
                    "LoyaltyIcon" to InlineTextContent(
                        placeholder = Placeholder(
                            14.sp,
                            height = 12.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                        )
                    ) {
                        Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
                    }
                ),
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LoyaltyContactEndBanner(modifier: Modifier = Modifier, isPrime: Boolean, onClose: () -> Unit) {
    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color(0xffffde82), Color(0xff99854e))),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(colors = listOf(Color(0xfffffcf2), Color(0xfffff5cc))),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(bottom = 14.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .padding(top = 16.dp, bottom = 9.dp, end = 4.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top
        ) {
            Image(painter = painterResource(R.drawable.great_text), contentDescription = null)
            Spacer(Modifier.width(4.dp))
            Text(
                buildAnnotatedString {
                    append("You've ")
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                        append("earned 40 points")
                    }
                    append(" for today.")
                },
                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                fontSize = 14.sp,
                lineHeight = 24.sp,
                color = textColorLight
            )
        }
        if(isPrime) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                        append("5 more days")
                    }
                    append(" to unlock ")
                    appendInlineContent("mbCrown")
                    append(" MB Prime")
                },
                inlineContent = mapOf(
                    "mbCrown" to InlineTextContent(
                        placeholder = Placeholder(
                            width = 17.sp,
                            height = 20.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Top
                        )
                    ) {
                        Box(
                            Modifier
                                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                                .padding(3.dp)
                        ) {
                            Image(painter = painterResource(R.drawable.ic_prime_crown), contentDescription = null)
                        }
                    }
                ),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
                color = textColorLight,
                modifier = Modifier.padding(top = 5.dp, start = 14.dp)
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            if (isPrime) {
                StreakBarPrime(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 18.dp, top = 6.dp), daysDone = 2
                )
                Text(
                    "MB Prime",
                    fontSize = 6.sp,
                    lineHeight = 7.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    color = textColorDark,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 12.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 2.dp, vertical = 1.dp)
                )
            }else {
                StreakBarNonPrime( modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 18.dp, top = 6.dp),daysDone = 2, showStarSeven = true)
            }
        }
    }
}

@Composable
fun LoyaltyContactReset1Banner(modifier: Modifier = Modifier,isPrime: Boolean, onClose: () -> Unit) {
    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color(0xffffde82), Color(0xff99854e))),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(colors = listOf(Color(0xfffffcf2), Color(0xfffff5cc))),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(bottom = 7.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 4.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom
        ) {
            GifAsyncImage(
                modifier = Modifier
                    .width(30.dp)
                    .height(45.dp), id = R.raw.loyality_reward_gif
            )
            Spacer(Modifier.width(8.dp))
            Image(painter = painterResource(R.drawable.get_rewarded_text), contentDescription = null)
        }
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                    append("When you Enquire")
                }
                append(" on Properties Daily")
            },
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
            color = textColorDark,
            modifier = Modifier.padding(top = 5.dp, start = 16.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            if (isPrime) {
                StreakBarPrime(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 18.dp, top = 6.dp), daysDone = 0
                )
                Text(
                    "MB Prime",
                    fontSize = 6.sp,
                    lineHeight = 7.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    color = textColorDark,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 12.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 2.dp, vertical = 1.dp)
                )
            }else {
                StreakBarNonPrime( modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 18.dp, top = 6.dp),daysDone = 0, showStarSeven = true)
                Text(
                    "+50 Points",
                    fontSize = 8.sp, lineHeight = 9.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    color = textColorDark,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 10.dp)
                        .border(width = 1.dp, color = Color(0xffeb860d), shape = RoundedCornerShape(50))
                        .background(color = Color.White, shape = RoundedCornerShape(50))
                        .padding(horizontal = 2.dp, vertical = 1.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 14.dp), contentAlignment = Alignment.CenterEnd
        ) {
            Canvas(
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(18.dp)
            ) {
                drawPath(
                    path = Path().apply {
                        lineTo(size.width - size.height / 2, 0f)
                        lineTo(size.width, size.height / 2)
                        lineTo(size.width - size.height / 2, size.height)
                        lineTo(0f, size.height)
                        lineTo(size.height / 2, size.height / 2)
                        lineTo(0f, 0f)
                        close()
                    },
                    brush = Brush.linearGradient(colors = listOf(Color(0xfffff7e1), Color(0xffffeebe)))
                )
            }
            Text(
                buildAnnotatedString {
                    appendInlineContent("LoyaltyIcon")
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                        append(" Earn")
                    }
                    append("40 points each day")
                },
                inlineContent = mapOf(
                    "LoyaltyIcon" to InlineTextContent(
                        placeholder = Placeholder(
                            14.sp,
                            height = 12.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                        )
                    ) {
                        Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
                    }
                ),
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LoyaltyContactCongratsMBPrimeBanner(modifier: Modifier = Modifier, onClose: () -> Unit) {
    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color(0xffffde82), Color(0xff99854e))),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(colors = listOf(Color(0xfffffcf2), Color(0xfffff5cc))),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(bottom = 7.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 4.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(painter = painterResource(R.drawable.congratulations_text), contentDescription = null)
        }
        Text(
            buildAnnotatedString {
                append("You've unlocked ")
                appendInlineContent("mbCrown")
                append(" MB Prime")
            },
            inlineContent = mapOf(
                "mbCrown" to InlineTextContent(
                    placeholder = Placeholder(
                        width = 17.sp,
                        height = 20.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Top
                    )
                ) {
                    Box(
                        Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                            .padding(3.dp)
                    ) {
                        Image(painter = painterResource(R.drawable.ic_prime_crown), contentDescription = null)
                    }
                }
            ),
            fontSize = 12.sp,
            lineHeight = 20.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
            color = textColorLight,
            modifier = Modifier.padding(top = 5.dp, start = 14.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            StreakBarPrime(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 18.dp, top = 6.dp), daysDone = 7
            )
            Text(
                "MB Prime",
                fontSize = 6.sp,
                lineHeight = 7.sp,
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                color = textColorDark,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 12.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 2.dp, vertical = 1.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 14.dp), contentAlignment = Alignment.CenterEnd
        ) {
            Canvas(
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(18.dp)
            ) {
                drawPath(
                    path = Path().apply {
                        lineTo(size.width - size.height / 2, 0f)
                        lineTo(size.width, size.height / 2)
                        lineTo(size.width - size.height / 2, size.height)
                        lineTo(0f, size.height)
                        lineTo(size.height / 2, size.height / 2)
                        lineTo(0f, 0f)
                        close()
                    },
                    brush = Brush.linearGradient(colors = listOf(Color(0xfffff7e1), Color(0xffffeebe)))
                )
            }
            Text(
                buildAnnotatedString {
                    appendInlineContent("LoyaltyIcon")
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                        append(" Earn")
                    }
                    append(" 40 points each day")
                },
                inlineContent = mapOf(
                    "LoyaltyIcon" to InlineTextContent(
                        placeholder = Placeholder(
                            14.sp,
                            height = 12.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                        )
                    ) {
                        Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
                    }
                ),
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LoyaltyContactCongratsNonPrimeBanner(modifier: Modifier = Modifier, onClose: () -> Unit) {
    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color(0xffffde82), Color(0xff99854e))),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(colors = listOf(Color(0xfffffcf2), Color(0xfffff5cc))),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(bottom = 7.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 4.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(painter = painterResource(R.drawable.congratulations_text), contentDescription = null)
        }
        Text(
            buildAnnotatedString {
                append("You've unlocked ")
                appendInlineContent("loyaltyIcon")
                append(" 100 points")
            },
            inlineContent = mapOf(
                "loyaltyIcon" to InlineTextContent(
                    placeholder = Placeholder(
                        width = 17.sp,
                        height = 20.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Top
                    )
                ) {
                    Box(
                        Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                            .padding(3.dp)
                    ) {
                        Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
                    }
                }
            ),
            fontSize = 12.sp,
            lineHeight = 20.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
            color = textColorLight,
            modifier = Modifier.padding(top = 5.dp, start = 14.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            StreakBarNonPrime(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 18.dp, top = 6.dp), daysDone = 7, showStarSeven = true
            )
            Text(
                "+50 Points",
                fontSize = 6.sp,
                lineHeight = 7.sp,
                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                color = Color(0xff009681),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 12.dp)
                    .border(width = 1.dp, color = Color(0xff009681), shape = RoundedCornerShape(4.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 2.dp, vertical = 1.dp)
            )
        }
    }
}

@Composable
fun LoyaltyContactNonPrimeMegaEndBanner(modifier: Modifier = Modifier, onClose: () -> Unit) {
    val image = painterResource(R.drawable.rays_loyalty)
    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color(0xffffde82), Color(0xff99854e))),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(colors = listOf(Color(0xfffffcf2), Color(0xfffff5cc))),
                shape = RoundedCornerShape(16.dp)
            )
            .drawBehind {
                with(image) {
                    draw(size = Size(size.width, size.height))
                }
            }
            .padding(bottom = 17.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 4.dp, start = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(R.drawable.congratulations_text), contentDescription = null)
        }
        Text(
            "Total points unlocked",
            fontSize = 12.sp,
            lineHeight = 20.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM),
            color = textColorLight,
            modifier = Modifier.padding(top = 15.dp)
        )
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.loyalty_coin),
                contentDescription = null,
                modifier = Modifier.width(30.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.width(7.dp))
            Text("400", fontFamily = getFontFamily(Constants.MONTSERRAT_BOLD), fontSize = 20.sp)
        }
        Spacer(Modifier.height(4.dp))
        Text(buildAnnotatedString {
            append("Collect these points to ")
            withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                append("get rewards")
            }
            append("worth ")
            withStyle(style = SpanStyle(color = mbRed)) {
                append("\u20b9")
            }
            withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD), color = mbRed)) {
                append("90K")
            }

        }, fontSize = 10.sp, lineHeight = 18.sp, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR), color = textColorDark)
        Spacer(Modifier.height(12.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .padding(top = 4.dp)
                    .padding(horizontal = 10.dp)
                    .border(
                        width = 1.dp,
                        brush = Brush.verticalGradient(colors = listOf(Color(0x66ffc72c), Color(0xffbf568c))),
                        shape = RoundedCornerShape(50)
                    )
                    .clip(RoundedCornerShape(50))
            ) {

            }
            Image(
                painter = painterResource(R.drawable.gradient_notch_bar), contentDescription = null,
                modifier = Modifier.background(color = Color(0xfffdf9ec).copy(alpha = 0.9f))
            )
        }
    }
}

@Composable
fun LoyaltyContactPrimeMegaEndBanner(modifier: Modifier = Modifier, onClose: () -> Unit) {
    val image = painterResource(R.drawable.rays_loyalty)
    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color(0xffffde82), Color(0xff99854e))),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(colors = listOf(Color(0xfffffcf2), Color(0xfffff5cc))),
                shape = RoundedCornerShape(16.dp)
            )
            .drawBehind {
                with(image) {
                    draw(size = Size(size.width, size.height))
                }
            }
            .padding(bottom = 21.dp, top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.congratulations_text), contentDescription = null)
        Image(painter = painterResource(R.drawable.ic_prime_crown), contentDescription = null, modifier = Modifier.size(70.dp))
        Text(
            "MB PRIME",
            fontSize = 22.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_BOLD),
            color = Color.Black,
            modifier = Modifier.padding(top = 15.dp)
        )
        Spacer(Modifier.height(4.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .padding(horizontal = 10.dp)
                    .border(
                        width = 1.dp,
                        brush = Brush.verticalGradient(colors = listOf(Color(0x66ffc72c), Color(0xffbf568c))),
                        shape = RoundedCornerShape(50)
                    )
                    .clip(RoundedCornerShape(50))
            ) {
                Text(
                    buildAnnotatedString {
                        append("Now, access phone numbers of ")
                        withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                            append("30 owners*")
                        }
                        append(" for ")
                        withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                            append("FREE")
                        }

                    },
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
            Image(
                painter = painterResource(R.drawable.gradient_notch_bar), contentDescription = null,
                modifier = Modifier.background(color = Color(0xfffdf9ec).copy(alpha = 0.9f))
            )
        }
        Spacer(Modifier.height(15.dp))
        Text(
            "*Limit of 1 property per day",
            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
            fontSize = 12.sp,
            color = textColorDark
        )
    }
}

@Composable
fun LoyaltyContactReset2Banner(modifier: Modifier = Modifier, isPrime: Boolean, onClose: () -> Unit) {
    var animateDone by remember { mutableStateOf(false) }
    var reverseDays by remember { mutableStateOf(false) }
    var resetDays by remember { mutableStateOf(4) }

    // Animate scale of the first text
    val scale by animateFloatAsState(
        targetValue = if (animateDone) 0.7f else 1f,
        animationSpec = tween(durationMillis = 600),
        label = "scaleAnim"
    )
    // Trigger the animation once shown
    LaunchedEffect(Unit) {
        delay(100)
        reverseDays = true
        while(resetDays>0){
            resetDays--
            delay(300)
        }
        delay(400) // Optional delay before starting
        animateDone = true
    }

    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color(0xffffde82), Color(0xff99854e))),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(colors = listOf(Color(0xfffffcf2), Color(0xfffff5cc))),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(bottom = 7.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 4.dp, start = 16.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    transformOrigin = TransformOrigin(0f, 0f)
                ),
            horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(R.drawable.oops_pink_emoticon), contentDescription = null)
            Spacer(Modifier.width(6.dp))
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(brush = Brush.linearGradient(colors = listOf(mbRed, Color(0xffffa8ae))))) {
                    append("Oops,")
                }
                append(" you missed your reward!")
            }, fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM), fontSize = 16.sp, lineHeight = 20.sp)
        }
        AnimatedVisibility(
            visible = animateDone,
            enter = fadeIn(animationSpec = tween(600))
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xff009681),
                            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                        )
                    ) {
                        append("Let's Restart!")
                    }
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                        append(" Enquire")
                    }
                    append(" on properties ")
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                        append("daily.")
                    }
                },
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            if (!reverseDays) {
                ResetStreakBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 18.dp, top = 6.dp), isPrime = isPrime, resetDays = resetDays
                )
            } else {
                if (isPrime) {
                    StreakBarPrime(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 18.dp, top = 6.dp), resetDays)
                } else {
                    StreakBarNonPrime(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 18.dp, top = 6.dp),resetDays)
                }
            }
            if (isPrime) {
                Text(
                    "MB Prime",
                    fontSize = 6.sp,
                    lineHeight = 7.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    color = textColorDark,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 12.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 2.dp, vertical = 1.dp)
                )
            } else {
                Text(
                    "+50 Points",
                    fontSize = 8.sp, lineHeight = 9.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    color = textColorDark,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 10.dp)
                        .border(width = 1.dp, color = Color(0xffeb860d), shape = RoundedCornerShape(50))
                        .background(color = Color.White, shape = RoundedCornerShape(50))
                        .padding(horizontal = 2.dp, vertical = 1.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 14.dp), contentAlignment = Alignment.CenterEnd
        ) {
            Canvas(
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(18.dp)
            ) {
                drawPath(
                    path = Path().apply {
                        lineTo(size.width - size.height / 2, 0f)
                        lineTo(size.width, size.height / 2)
                        lineTo(size.width - size.height / 2, size.height)
                        lineTo(0f, size.height)
                        lineTo(size.height / 2, size.height / 2)
                        lineTo(0f, 0f)
                        close()
                    },
                    brush = Brush.linearGradient(colors = listOf(Color(0xfffff7e1), Color(0xffffeebe)))
                )
            }
            Text(
                buildAnnotatedString {
                    appendInlineContent("LoyaltyIcon")
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                        append(" Earn")
                    }
                    append("40 points each day")
                },
                inlineContent = mapOf(
                    "LoyaltyIcon" to InlineTextContent(
                        placeholder = Placeholder(
                            14.sp,
                            height = 12.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                        )
                    ) {
                        Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
                    }
                ),
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LoyaltyContactStreakPrimeIntro(modifier: Modifier = Modifier, onClose: () -> Unit) {
    // Load Lottie animations
    val boxAnimSpec = rememberLottieComposition(LottieCompositionSpec.Asset("open-gift.json"))
    val outsideAnimSpec = rememberLottieComposition(LottieCompositionSpec.Asset("desktop-confetti-blown-up.json"))
    var showBoxContent by remember { mutableStateOf(false) }
    var showOutsideAnimation by remember { mutableStateOf(false) }
    var showSlideUpContent by remember { mutableStateOf(false) }

    // Lottie animation progress
    val boxAnimProgress by animateLottieCompositionAsState(
        composition = boxAnimSpec.value,
        iterations = 1,
        speed = 5f,
        isPlaying = true
    )
    val outsideAnimProgress by animateLottieCompositionAsState(
        composition = outsideAnimSpec.value,
        iterations = 1,
        speed = 1f,
        isPlaying = showOutsideAnimation,
    )
    LaunchedEffect(boxAnimProgress) {
        if (boxAnimProgress >= 1f && !showBoxContent) {
            showBoxContent = true
            showOutsideAnimation = true
        }
    }
    // Listen to outside animation end
    LaunchedEffect(outsideAnimProgress) {
        if (outsideAnimProgress >= 1f && !showSlideUpContent) {
            showSlideUpContent = true
            showOutsideAnimation = false
        }
    }

    val daysDone = 3
    val image = painterResource(id = R.drawable.loyalty_contact_streak_bg)
    val image1 = painterResource(id = R.drawable.rays_loyalty)
    Column(
        modifier = modifier
            .drawBehind {
                with(image) {
                    draw(size = Size(size.width, size.height))
                }
                with(image1) {
                    draw(size = Size(size.width, (size.height) * 0.6f))
                }
            }
            .padding(top = 28.dp, bottom = 21.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier
            .fillMaxWidth()) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                MegaIntroTitle(modifier = Modifier.fillMaxWidth(fraction = 0.7f))
                if (!showBoxContent) {
                    LottieAnimation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        composition = boxAnimSpec.value,
                        progress = { boxAnimProgress }
                    )
                } else {
                    PrimeRewardDetailWithBg(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .padding(horizontal = 30.dp), contentAlignment = Alignment.TopCenter
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .border(
                                width = 1.dp,
                                brush = Brush.verticalGradient(colors = listOf(Color(0x66ffc72c), Color(0xffbf568c))),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(top = 12.dp, bottom = 10.dp, start = 6.dp, end = 6.dp)
                    ) {
                        Text(buildAnnotatedString {
                            append("Get access to ")
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                                    brush = Brush.linearGradient(colors = listOf(Color(0xffffc72c), mbRed))
                                )
                            ) {
                                append("Exclusive Owner Properties")
                            }
                        }, fontSize = 12.sp, lineHeight = 16.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    }
                    Text(
                        buildAnnotatedString {
                            append("worth ")
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                                    color = mbRed
                                )
                            ) {
                                append("₹1,899")
                            }
                            append(" for ")
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                                    color = Color(0xff009681)
                                )
                            ) {
                                append("FREE")
                            }
                        },
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                        modifier = Modifier
                            .background(color = Color(0xfffdf9ec).copy(alpha = 0.9f))
                            .padding(horizontal = 12.dp)
                    )
                }
                AnimatedVisibility(
                    visible = showSlideUpContent,
                    enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
                ) {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Canvas(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .width(90.dp)
                        ) {
                            val arrowWidth = 40
                            val centerX = size.width / 2
                            val centerY = size.height / 2
                            val colorWithOpacity = textColorDark.copy(alpha = 0.30f)

                            // Draw left part of the horizontal line (before the arrow)
                            drawLine(
                                color = colorWithOpacity,
                                start = Offset(0f, centerY),
                                end = Offset(centerX - arrowWidth / 2, centerY),
                                strokeWidth = 1f
                            )

                            // Draw right part of the horizontal line (after the arrow)
                            drawLine(
                                color = colorWithOpacity,
                                start = Offset(centerX + arrowWidth / 2, centerY),
                                end = Offset(size.width, centerY),
                                strokeWidth = 1f
                            )
                            drawLine(
                                color = colorWithOpacity,
                                start = Offset(centerX - arrowWidth / 2, centerY),
                                end = Offset(centerX, centerY + arrowWidth / 2),
                                strokeWidth = 1f
                            )
                            drawLine(
                                color = colorWithOpacity,
                                start = Offset(centerX, centerY + arrowWidth / 2),
                                end = Offset(centerX + arrowWidth / 2, centerY),
                                strokeWidth = 1f
                            )
                        }
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 17.dp)
                        ) {
                            DottedBorderBox(
                                borderColor = Color(0xffffd86a),
                                cornerRadius = 16.dp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 13.dp)
                                    .padding(horizontal = 12.dp)
                                    .background(color = Color(0xb3ffffff))
                            ) {
                                Column {
                                    IntroStreakBar(modifier = Modifier, daysDone)
                                }
                            }
                            Box(contentAlignment = Alignment.Center) {
                                Canvas(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(26.dp)
                                        .padding(horizontal = 36.dp)
                                ) {
                                    drawPath(
                                        path = Path().apply {
                                            lineTo(size.width, 0f)
                                            lineTo(size.width - size.height / 2, size.height / 2)
                                            lineTo(size.width, size.height)
                                            lineTo(0f, size.height)
                                            lineTo(0f + size.height / 2, size.height / 2)
                                            lineTo(0f, 0f)
                                            close()
                                        },
                                        color = Color(0xfffff7e1)
                                    )
                                }
                                Text(
                                    buildAnnotatedString {
                                        append("Additionally, earn ")
                                        appendInlineContent("LoyaltyIcon")
                                        withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                                            append(" 50 points each day")
                                        }
                                    },
                                    inlineContent = mapOf(
                                        "LoyaltyIcon" to InlineTextContent(
                                            placeholder = Placeholder(
                                                14.sp,
                                                height = 12.sp,
                                                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                                            )
                                        ) {
                                            Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
                                        }
                                    ),
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                    fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
                                )
                            }
                        }
                        Text(
                            "Got it",
                            color = mbRed,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 21.dp)
                                .noRippleClick { onClose() }
                                .padding(vertical = 6.dp, horizontal = 16.dp)
                        )
                    }
                }
            }
            if (showOutsideAnimation) {
                LottieAnimation(
                    composition = outsideAnimSpec.value,
                    progress = { outsideAnimProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
fun LoyaltyContactStreakNonPrimeIntro(modifier: Modifier = Modifier, onClose: () -> Unit) {
    val daysDone = 3
    val image = painterResource(id = R.drawable.loyalty_contact_streak_bg)
    val image1 = painterResource(id = R.drawable.rays_loyalty)
    Column(
        modifier = modifier
            .drawBehind {
                with(image) {
                    draw(size = Size(size.width, size.height))
                }
                with(image1) {
                    draw(size = Size(size.width, (size.height) * 0.6f))
                }
            }
            .padding(top = 28.dp, bottom = 21.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MegaIntroTitle(modifier = Modifier.fillMaxWidth(fraction = 0.7f))
        NonPrimeRewardDetailWithBg(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 12.dp)
                .border(
                    width = 1.dp,
                    brush = Brush.verticalGradient(colors = listOf(Color(0x66ffc72c), Color(0xffbf568c))),
                    shape = RoundedCornerShape(50)
                )
        ) {
            Box(
                Modifier
                    .height(42.dp)
                    .width(90.dp), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.loyalty_reward_scroll_edge),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    buildAnnotatedString {
                        append("Rewards worth ")
                        withStyle(
                            style = SpanStyle(
                                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                                brush = Brush.linearGradient(colors = listOf(Color(0xffffc72c), mbRed))
                            )
                        ) {
                            append(" \u20B9")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = getFontFamily(Constants.MONTSERRAT_BOLD),
                                brush = Brush.linearGradient(colors = listOf(Color(0xffffc72c), mbRed))
                            )
                        ) {
                            append("90K")
                        }
                    },
                    fontSize = 10.sp,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    lineHeight = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 12.dp)
                )
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        ) {
            DottedBorderBox(
                borderColor = Color(0xffffd86a),
                cornerRadius = 16.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 13.dp)
                    .padding(horizontal = 12.dp)
                    .background(color = Color(0xb3ffffff))
            ) {
                Column {
                    IntroStreakBar(modifier = Modifier, daysDone)
                }
            }
            Box(contentAlignment = Alignment.Center) {
                Canvas(
                    Modifier
                        .fillMaxWidth()
                        .height(26.dp)
                        .padding(horizontal = 36.dp)
                ) {
                    drawPath(
                        path = Path().apply {
                            lineTo(size.width, 0f)
                            lineTo(size.width - size.height / 2, size.height / 2)
                            lineTo(size.width, size.height)
                            lineTo(0f, size.height)
                            lineTo(0f + size.height / 2, size.height / 2)
                            lineTo(0f, 0f)
                            close()
                        },
                        color = Color(0xfffff7e1)
                    )
                }
                Text(
                    buildAnnotatedString {
                        append("Additionally, earn ")
                        appendInlineContent("LoyaltyIcon")
                        withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                            append(" 50 points each day")
                        }
                    },
                    inlineContent = mapOf(
                        "LoyaltyIcon" to InlineTextContent(
                            placeholder = Placeholder(
                                14.sp,
                                height = 12.sp,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                            )
                        ) {
                            Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
                        }
                    ),
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
                )
            }
        }
        Text(
            "Got it",
            color = mbRed,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
            modifier = Modifier
                .padding(top = 20.dp, bottom = 21.dp)
                .noRippleClick { onClose() }
                .padding(vertical = 6.dp, horizontal = 16.dp)
        )
    }
}

@Composable
private fun MegaIntroTitle(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) { append("Enquire") }
            append(" on Properties Daily for the ")
            withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) { append("Next 7 Days") }
        },
        fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
        color = textColorDark,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun PrimeRewardDetailWithBg(modifier: Modifier = Modifier) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//        Image(
//            painter = painterResource(R.drawable.rays_loyalty),
//            contentDescription = null,
//            contentScale = ContentScale.FillWidth,
//            modifier = Modifier.fillMaxWidth()
//        )
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.get_rewarded_text),
                contentDescription = null,
                modifier = Modifier.height(40.dp)
            )
            Spacer(Modifier.height(4.dp))
            Image(
                painter = painterResource(R.drawable.ic_prime_crown),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "MB PRIME",
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontFamily = getFontFamily(Constants.MONTSERRAT_BOLD),
                color = Color.Black
            )
        }
    }
}

@Composable
private fun NonPrimeRewardDetailWithBg(modifier: Modifier = Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.earn_points_daily_text),
            contentDescription = null,
            modifier = Modifier.height(40.dp)
        )
        Text("Collect", fontSize = 12.sp, color = textColorLight, fontFamily = getFontFamily(Constants.MONTSERRAT_MEDIUM))
        Image(
            painter = painterResource(R.drawable.loyalty_coin),
            contentDescription = null,
            modifier = Modifier.size(44.dp), alpha = 0.7f
        )
        Text(
            "400 Points",
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_BOLD),
            color = Color.Black
        )
        Text(
            "Use these points to claim",
            fontSize = 12.sp,
            fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
            color = Color.Black
        )
        Image(painter = painterResource(R.drawable.gradient_notch_bar), contentDescription = null)
    }
}

@Composable
private fun IntroStreakBar(modifier: Modifier = Modifier, daysDone: Int) {
    Row(
        modifier.padding(top = 23.dp, bottom = 16.dp, start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Days",
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
            fontSize = 8.sp,
            lineHeight = 18.sp,
            color = textColorDark
        )
        Spacer(Modifier.width(4.dp))
        StreakBarPrime(modifier = Modifier.weight(1f), daysDone)
    }
}

@Composable
private fun StreakBarPrime(modifier: Modifier = Modifier, daysDone: Int) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Row(
            Modifier
                .align(Alignment.CenterEnd)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(6) { index ->
                if (index + 1 <= daysDone) {
                    Row(Modifier.weight(if (index == 0) 0.8f else 1f), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier
                                .weight(1f)
                                .height(3.dp)
                                .background(
                                    Color(0xff009681),
                                    shape = if (index == 0) RoundedCornerShape(
                                        topStartPercent = 50,
                                        bottomStartPercent = 50
                                    ) else RectangleShape
                                )
                        )
                        DaysDoneItem(Modifier)
                        if (daysDone != 7 && index + 1 == daysDone) {
                            Box(
                                Modifier
                                    .weight(0.3f)
                                    .height(3.dp)
                                    .background(
                                        Color(0xff009681),
                                        shape = RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50)
                                    )
                            )
                        }
                    }
                } else {
                    Row(Modifier.weight(if (index == 0) 0.8f else 1f), verticalAlignment = Alignment.CenterVertically) {
                        if (index == 0) {
                            Box(
                                Modifier
                                    .weight(0.3f)
                                    .height(3.dp)
                                    .background(Color(0xff009681), shape = RoundedCornerShape(50))
                            )
                        }
                        Box(
                            Modifier
                                .weight(1f)
                                .height(3.dp)
                                .background(textColorExtraLight.copy(alpha = 0.20f), shape = RoundedCornerShape(50))
                        )
                        DaysNotDoneItem(Modifier, "${index + 1}")
                    }
                }
            }
            Row(Modifier.weight(1.2f), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .weight(1f)
                        .height(3.dp)
                        .background(
                            if (daysDone == 7) Color(0xff009681) else textColorExtraLight.copy(alpha = 0.20f),
                            shape = RoundedCornerShape(50)
                        )
                )
                Box(
                    Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(R.drawable.ic_prime_crown), contentDescription = null)
                }
            }
        }
    }
}

@Composable
private fun StreakBarNonPrime(modifier: Modifier = Modifier, daysDone: Int, showStarSeven: Boolean = false) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Row(
            Modifier
                .align(Alignment.CenterEnd)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(6) { index ->
                if (index + 1 <= daysDone) {
                    Row(Modifier.weight(if (index == 0) 0.8f else 1f), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier
                                .weight(1f)
                                .height(3.dp)
                                .background(
                                    Color(0xff009681),
                                    shape = if (index == 0) RoundedCornerShape(
                                        topStartPercent = 50,
                                        bottomStartPercent = 50
                                    ) else RectangleShape
                                )
                        )
                        DaysDoneItem(Modifier)
                        if (daysDone != 7 && index + 1 == daysDone) {
                            Box(
                                Modifier
                                    .weight(0.3f)
                                    .height(3.dp)
                                    .background(
                                        Color(0xff009681),
                                        shape = RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50)
                                    )
                            )
                        }
                    }
                } else {
                    Row(Modifier.weight(if (index == 0) 0.8f else 1f), verticalAlignment = Alignment.CenterVertically) {
                        if (index == 0) {
                            Box(
                                Modifier
                                    .weight(0.3f)
                                    .height(3.dp)
                                    .background(Color(0xff009681), shape = RoundedCornerShape(50))
                            )
                        }
                        Box(
                            Modifier
                                .weight(1f)
                                .height(3.dp)
                                .background(textColorExtraLight.copy(alpha = 0.20f), shape = RoundedCornerShape(50))
                        )
                        DaysNotDoneItem(Modifier, "${index + 1}")
                    }
                }
            }
            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .weight(1f)
                        .height(3.dp)
                        .background(
                            if (daysDone == 7) Color(0xff009681) else textColorExtraLight.copy(alpha = 0.20f),
                            shape = RoundedCornerShape(50)
                        )
                )
                if (showStarSeven) {
                    Box(
                        Modifier
                            .size(28.dp)
                            .clip(CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(if (daysDone == 7) R.drawable.contact_streak_point7_green else R.drawable.contact_streak_point7),
                            contentDescription = null, contentScale = ContentScale.FillWidth, modifier = Modifier.size(28.dp)
                        )
                    }
                } else {
                    if (daysDone != 7) {
                        DaysNotDoneItem(Modifier, "7")
                    } else {
                        DaysDoneItem(Modifier)
                    }
                }
            }
        }
    }
}

@Composable
private fun ResetStreakBar(modifier: Modifier = Modifier, isPrime: Boolean=true,resetDays: Int) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Row(
            Modifier
                .align(Alignment.CenterEnd)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(6) { index ->
                if (index + 1 < resetDays) {
                    Row(Modifier.weight(if (index == 0) 0.8f else 1f), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier
                                .weight(1f)
                                .height(3.dp)
                                .background(
                                    Color(0xff009681),
                                    shape = if (index == 0) RoundedCornerShape(
                                        topStartPercent = 50,
                                        bottomStartPercent = 50
                                    ) else RectangleShape
                                )
                        )
                        DaysDoneItem(Modifier)
                    }
                } else if (index + 1 == resetDays) {
                    Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier
                                .weight(1f)
                                .height(3.dp)
                                .background(
                                    Color(0xffFFCCD1),
                                    shape = RectangleShape
                                )
                        )
                        DaysMissItem(Modifier)
                    }
                } else {
                    Row(Modifier.weight(if (index == 0) 0.8f else 1f), verticalAlignment = Alignment.CenterVertically) {
                        if (index == 0) {
                            Box(
                                Modifier
                                    .weight(0.3f)
                                    .height(3.dp)
                                    .background(Color(0xff009681), shape = RoundedCornerShape(50))
                            )
                        }
                        Box(
                            Modifier
                                .weight(1f)
                                .height(3.dp)
                                .background(textColorExtraLight.copy(alpha = 0.20f), shape = RoundedCornerShape(50))
                        )
                        DaysNotDoneItem(Modifier, "${index + 1}")
                    }
                }
            }
            Row(Modifier.weight(1.2f), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .weight(1f)
                        .height(3.dp)
                        .background(
                            if (resetDays == 7) Color(0xffFFCCD1) else textColorExtraLight.copy(alpha = 0.20f),
                            shape = RoundedCornerShape(50)
                        )
                )
                if (isPrime) {
                    Box(
                        Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(painter = painterResource(R.drawable.ic_prime_crown), contentDescription = null)
                    }
                } else {
                    Box(
                        Modifier
                            .size(28.dp)
                            .clip(CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.contact_streak_point7),
                            contentDescription = null, contentScale = ContentScale.FillWidth, modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DaysDoneItem(modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(Color(0xff009681)), contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Default.Done,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(12.dp)
        )
    }
}

@Composable
private fun DaysMissItem(modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(20.dp)
            .shadow(elevation = 4.dp, shape = CircleShape)
            .clip(CircleShape)
            .border(width = 1.dp, Color(0xffFFCCD1), shape = CircleShape)
            .background(color = Color.White, shape = CircleShape), contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Rounded.Clear,
            contentDescription = null,
            tint = mbRed,
            modifier = Modifier.size(12.dp)
        )
    }
}

@Composable
private fun DaysNotDoneItem(modifier: Modifier = Modifier, text: String) {
    Text(
        text,
        modifier = modifier
            .size(20.dp)
            .clip(CircleShape)
            .border(width = 1.dp, color = Color(0x80909090), shape = CircleShape)
            .background(color = Color.White),
        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
        color = textColorExtraLight,
        fontSize = 10.sp, textAlign = TextAlign.Center
    )
}
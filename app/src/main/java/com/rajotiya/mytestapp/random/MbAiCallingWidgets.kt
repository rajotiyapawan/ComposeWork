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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbGreen
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.utility.circulatingGradient
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 28-11-2025.
 */

@Composable
fun MbAiHomePageSearchWidget(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(brush = Brush.linearGradient(colors = listOf(Color(0xff6a060a),mbRed)))
            .padding(top = 12.dp, bottom = 20.dp)
            .padding(horizontal = 16.dp)
            .border(
                width = 2.dp, brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xfffd5962), Color(0xffec8fff), Color(0xffb29eff)
                    )
                ), shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xfff1ffff), Color(0xfffff3f9)
                    )
                ), shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
    ) {
        Image(painterResource(R.drawable.ai_badge), contentDescription = null, modifier = Modifier.size(10.dp))
        Spacer(Modifier.width(4.dp))
        Column(
            Modifier
                .weight(1f)
                .padding(end = 14.dp)
        ) {
            Image(painterResource(R.drawable.ic_ai_assistant_text), contentDescription = null)
            Spacer(Modifier.height(4.dp))
            Text(
                "I can search properties for you, find new listings, and share recommendations",
                fontSize = 12.sp,
                lineHeight = 17.sp,
                fontFamily = getFontFamily(weight = FontWeight.Medium),
                color = textColorDark
            )
            Spacer(Modifier.height(8.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color(0x8cc3c3c3), shape = RoundedCornerShape(50))
                    .background(color = Color.White, shape = RoundedCornerShape(50)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Tell me what you're looking for",
                    fontSize = 12.sp,
                    lineHeight = 22.sp,
                    fontFamily = getFontFamily(),
                    color = textColorExtraLight,
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp, bottom = 8.dp)
                )
                Image(
                    painterResource(R.drawable.ai_badge),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(30.dp)
                )
            }
        }
    }
}

@Composable
fun MbAiCallDone(modifier: Modifier = Modifier) {
    val myInlineContent = mapOf(
        "myAiIcon" to InlineTextContent(
            placeholder = Placeholder(width = 18.sp, height = 19.sp, placeholderVerticalAlign = PlaceholderVerticalAlign.Bottom)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_ai_text), contentDescription = null)
        })
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(2.dp)
            .drawBehind {
                drawRoundRect(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFFFF3F9), Color(0xFFF1FFFF)),
                        start = Offset(0f, size.height),        // BottomStart
                        end = Offset(size.width, 0f)            // TopEnd
                    ),
                    cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
                )
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                buildAnnotatedString {
                    append("Let ")
                    appendInlineContent("myAiIcon")
                    append(" make call for you")
                }, inlineContent = myInlineContent,
                fontSize = 12.sp, lineHeight = 17.sp,
                color = textColorDark,
                fontFamily = getFontFamily(weight = FontWeight.SemiBold)
            )
            Box(
                Modifier
                    .background(Color.White, shape = RoundedCornerShape(50))
                    .circulatingGradient()
            ) {
                Row {
                    Image(painter = painterResource(R.drawable.ic_ai_call), contentDescription = null)
                    Spacer(Modifier.width(3.dp))
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xffeb2127),
                                            Color(0xff9020cc)
                                        )
                                    )
                                )
                            ) {
                                append("Start Call")
                            }
                        },
                        fontSize = 12.sp,
                        textDecoration = TextDecoration.Underline,
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MbAiAssistantConsentScreen(modifier: Modifier = Modifier, onClick: (String, String) -> Unit) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    val outsideAnimSpec =
        rememberLottieComposition(LottieCompositionSpec.Asset("falling-confetti.json"))
    val progress by animateLottieCompositionAsState(
        outsideAnimSpec.value, // <-- use .value
        iterations = LottieConstants.IterateForever,
    )
    Box(
        modifier
    ) {
        LottieAnimation(
            composition = outsideAnimSpec.value,
            progress = { progress },
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f),
            contentScale = ContentScale.FillHeight
        )
        ConsentScreenTopSection(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter), onClick = onClick
        )
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CongratsBlock(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                showBottomSheet = true
            }
        }

        // bottom section
        BottomSticky(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.BottomCenter), onClick = onClick
        )

    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = Color.Transparent,
            scrimColor = Color(0xcc000000),
            dragHandle = {}
        ) {
            ConsentPopUp(
                onContinue = { showBottomSheet = false }
            )
        }
    }
}


@Composable
fun BottomSheetContent(onClose: () -> Unit) {
    val list = remember {
        listOf(
            "Call sellers on your behalf & check availability",
            "Connect with Top Agents on your request",
            "Share personalised recommendations",
            "Create notes for projects you like"
        )
    }
    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, end = 16.dp), contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painterResource(R.drawable.share_prop_close_icon),
                contentDescription = null,
                modifier = Modifier.noRippleClick { onClose() })
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Text(
                "It will support you with:",
                color = textColorDark,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontFamily = getFontFamily(weight = FontWeight.SemiBold)
            )

            Spacer(Modifier.height(12.dp))

            repeat(list.size) { index ->

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(R.drawable.ic_tick_green), contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        list[index],
                        color = textColorDark,
                        fontSize = 12.sp,
                        fontFamily = getFontFamily()
                    )
                }
            }
        }
    }
}

@Composable
private fun ConsentScreenTopSection(modifier: Modifier, onClick: (String, String) -> Unit) {
    Box(modifier, contentAlignment = Alignment.CenterEnd) {
        Text(
            "Skip",
            color = textColorDark,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .noRippleClick { onClick("skip", "N") })
    }
}

@Composable
private fun CongratsBlock(modifier: Modifier = Modifier, onHowClick: () -> Unit) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp))
                .background(
                    color = Color(0xfff5f5f5), shape = RoundedCornerShape(20.dp)
                )
                .padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .background(
                        color = Color.White, shape = RoundedCornerShape(20.dp)
                    )
                    .padding(bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.drawable.ai_badge), contentDescription = null)
                Text(
                    "Congrats!",
                    color = mbGreen,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold)
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Image(painter = painterResource(R.drawable.ic_ai_assistant_text), contentDescription = null)
                    Box(
                        Modifier
                            .padding(start = 4.dp, top = 8.dp)
                            .border(width = 1.dp, color = Color(0xffffebb3), shape = RoundedCornerShape(4.dp))
                            .background(color = Color(0xffffc72c), shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 2.dp, vertical = 1.dp)
                    ) {
                        Text(
                            "FREE",
                            color = textColorDark,
                            fontFamily = getFontFamily(weight = FontWeight.Bold),
                            fontSize = 8.sp,
                            lineHeight = 8.sp
                        )
                    }
                }
                Text(
                    "Exclusively Activated for you",
                    color = textColorLight,
                    fontFamily = getFontFamily(weight = FontWeight.Medium),
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )
            }
            CallingInfo(Modifier.fillMaxWidth(0.9f))
            Text(
                "+91-120-52XXXXX",
                color = mbGreen,
                fontFamily = getFontFamily(weight = FontWeight.Bold),
                fontSize = 22.sp, textAlign = TextAlign.Center,
                lineHeight = 26.sp, modifier = Modifier.padding(top = 16.dp)
            )
        }
        Text(
            "How your AI Assistant helps you?",
            color = textColorDark,
            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
            fontSize = 14.sp, textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            lineHeight = 16.sp, modifier = Modifier
                .noRippleClick {
                    onHowClick()
                }
                .padding(top = 30.dp)
        )
    }
}


@Composable
private fun CallingInfo(modifier: Modifier = Modifier) {
    val outsideAnimSpec1 =
        rememberLottieComposition(LottieCompositionSpec.Asset("call_arrow_anim.json"))
    val progress1 by animateLottieCompositionAsState(
        outsideAnimSpec1.value, // <-- use .value
        iterations = 20,
    )
    Row(modifier) {
        Box(Modifier.background(color = Color(0xfffffcf2), shape = CircleShape), contentAlignment = Alignment.Center) {
            LottieAnimation(
                composition = outsideAnimSpec1.value,
                progress = { progress1 },
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(
            "To connect with the Seller, you’ll receive an AI call to assist you",
            color = textColorDark,
            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
            fontSize = 14.sp, textAlign = TextAlign.Center,
            lineHeight = 20.sp, modifier = Modifier
        )
    }
}


@Composable
private fun BottomSticky(modifier: Modifier = Modifier, onClick: (String, String) -> Unit) {
    Box(
        modifier
            .noRippleClick { onClick("continue", "Y") }
            .background(color = mbRed, shape = RoundedCornerShape(50))
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Let AI Assistant Call me",
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = getFontFamily(weight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun ConsentPopUp(modifier: Modifier = Modifier, onContinue: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier
                .padding(horizontal = 12.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xffedfaf9),
                            Color.White
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val outsideAnimSpec =
                rememberLottieComposition(LottieCompositionSpec.Asset("confetti_popper.json"))
            val progress by animateLottieCompositionAsState(
                outsideAnimSpec.value, // <-- use .value
                iterations = LottieConstants.IterateForever,
            )
            LottieAnimation(
                composition = outsideAnimSpec.value,
                progress = { progress },
                modifier = Modifier
                    .size(80.dp)
            )
            val inlineContent = mapOf(
                "aiText" to InlineTextContent(
                    placeholder = Placeholder(
                        width = 20.sp,
                        height = 20.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextBottom
                    )
                ) {
                    Image(
                        modifier = Modifier.width(20.dp),
                        painter = painterResource(R.drawable.ic_ai_text),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                }
            )
            Text(
                buildAnnotatedString {
                    append("Congrats! ")
                    appendInlineContent("aiText")
                    append(" Calls are now active for your account.")
                },
                textAlign = TextAlign.Center,
                inlineContent = inlineContent,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                color = textColorDark
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Get connected to buyer responses instantly through AI calls.",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                fontFamily = getFontFamily(weight = FontWeight.Medium),
                color = textColorDark
            )
            Spacer(Modifier.height(24.dp))
            Column(
                modifier
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp))
                    .background(
                        color = Color.White, shape = RoundedCornerShape(20.dp)
                    )
                    .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val outsideAnimSpec1 =
                    rememberLottieComposition(LottieCompositionSpec.Asset("call_arrow_anim.json"))
                val progress1 by animateLottieCompositionAsState(
                    outsideAnimSpec1.value, // <-- use .value
                    iterations = LottieConstants.IterateForever,
                )
                Box(
                    Modifier
                        .size(42.dp)
                        .background(color = Color(0xfffffcf2), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    LottieAnimation(
                        composition = outsideAnimSpec1.value,
                        progress = { progress1 },
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    "To speak with Buyers waiting on the call",
                    lineHeight = 20.sp,
                    fontSize = 12.sp, color = textColorLight,
                    fontFamily = getFontFamily(weight = FontWeight.Medium)
                )
                Text(
                    "Answer calls from",
                    fontSize = 16.sp,
                    lineHeight = 26.sp,
                    color = textColorDark,
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold)
                )
                Text(
                    "+91-120-52XXXXX",
                    fontSize = 22.sp,
                    lineHeight = 26.sp,
                    color = mbGreen,
                    fontFamily = getFontFamily(weight = FontWeight.Bold)
                )
            }
            Spacer(Modifier.height(24.dp))
            Text(
                "AI Calls: How Buyers Get Connected to You",
                fontSize = 12.sp,
                lineHeight = 22.sp,
                color = textColorDark, modifier = Modifier.fillMaxWidth(),
                fontFamily = getFontFamily(weight = FontWeight.SemiBold)
            )

            val list =
                remember { listOf("Buyer Response Received", "AI Calls Buyer", "Checks Availability", "Connects with you") }
            val icons =
                remember {
                    listOf(
                        R.drawable.ic_message_ticked,
                        R.drawable.ic_phone_stars,
                        R.drawable.ic_chat_bubbles_question,
                        R.drawable.ic_calls_connected
                    )
                }

            list.forEachIndexed { index, string ->
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
                            .size(36.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .padding(5.dp), contentAlignment = Alignment.Center
                    ) {
                        Image(painterResource(icons[index]), contentDescription = null)
                    }
                    Spacer(Modifier.width(17.dp))
                    Text(
                        string,
                        fontSize = 12.sp,
                        lineHeight = 26.sp,
                        color = textColorDark,
                        fontFamily = getFontFamily(weight = FontWeight.Medium)
                    )
                }
                if (index < icons.size - 1) {
                    Spacer(Modifier.height(4.dp))
                }
            }

            Box(
                Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .noRippleClick { onContinue() }
                    .background(color = mbRed, shape = RoundedCornerShape(50))
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Continue",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = getFontFamily(weight = FontWeight.SemiBold)
                )
            }

        }
    }
}
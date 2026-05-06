package com.til.mb.ai_calling.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 28-11-2025.
 */

@Preview
@Composable
fun MbAiAssistantConsentScreen1(modifier: Modifier = Modifier) {
    Box(
        modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,Color(0xfffff3f9), Color(0xfff2fbfc),Color(0xfff2fbfc), Color.White
                    )
                )
            )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // top section
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Text("Skip", color = textColorDark, modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp))
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 9.dp)
                        .border(width = 1.dp, color = Color(0xffd7d7d7), shape = RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Column {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xffedfaf9))
                                .padding(top = 7.dp, bottom = 5.dp, start = 13.dp, end = 13.dp)
                        ) {
                            Text(
                                "Thanks for showing interest in this property!", fontSize = 12.sp,
                                lineHeight = 24.sp,
                                color = textColorDark,
                                fontFamily = getFontFamily(weight = FontWeight.Medium)
                            )
                        }
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(
                                buildAnnotatedString {
                                    append("You can find contact details on ")
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                                            color = textColorDark
                                        )
                                    ) {
                                        append("My Activity")
                                    }
                                    append("\npage & your email ID")
                                },
                                fontSize = 12.sp,
                                lineHeight = 20.sp,
                                color = textColorLight,
                                fontFamily = getFontFamily(weight = FontWeight.Normal)
                            )
                        }
                    }
                }
            }


            val outsideAnimSpec =
                rememberLottieComposition(LottieCompositionSpec.Asset("green_tick_anim.json"))
            val progress by animateLottieCompositionAsState(
                outsideAnimSpec.value, // <-- use .value
                iterations = 20,
            )
            LottieAnimation(
                composition = outsideAnimSpec.value,
                progress = { progress },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(64.dp)
            )
            Text("Congratulations!", color = textColorDark, fontSize = 18.sp, lineHeight = 22.sp, fontFamily = getFontFamily())
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    "Your personal ",
                    color = textColorDark,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontFamily = getFontFamily(weight = FontWeight.Bold)
                )
                Image(painter = painterResource(R.drawable.ic_ai_assistant_text), contentDescription = null)
                Box(
                    Modifier
                        .padding(start = 4.dp)
                        .border(width = 1.dp, color = Color(0xffffebb3), shape = RoundedCornerShape(4.dp))
                        .background(color = Color(0xffffc72c), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 3.dp, vertical = 2.dp)
                ) {
                    Text(
                        "FREE",
                        color = textColorDark,
                        fontFamily = getFontFamily(weight = FontWeight.Bold),
                        fontSize = 11.sp,
                        lineHeight = 12.sp
                    )
                }
            }
            Text(
                "Now exclusively Activated for you",
                color = textColorLight,
                fontFamily = getFontFamily(weight = FontWeight.Medium),
                fontSize = 14.sp,
                lineHeight = 17.sp
            )

            Row(Modifier.padding(top = 40.dp, bottom = 30.dp)) {
                Box(
                    Modifier
                        .width(67.dp)
                        .height(1.dp)
                        .background(brush = Brush.linearGradient(colors = listOf(Color(0x00909090), Color(0x4c909090))))
                )
                Spacer(Modifier.width(4.dp))
                Box(
                    Modifier
                        .size(7.dp)
                        .background(color = Color(0xffd6dbdb), shape = CircleShape)
                )
                Spacer(Modifier.width(4.dp))
                Box(
                    Modifier
                        .width(67.dp)
                        .height(1.dp)
                        .background(brush = Brush.linearGradient(colors = listOf(Color(0x4c909090), Color(0x00909090))))
                )
            }

            val outsideAnimSpec1 =
                rememberLottieComposition(LottieCompositionSpec.Asset("call_arrow_anim.json"))
            val progress1 by animateLottieCompositionAsState(
                outsideAnimSpec.value, // <-- use .value
                iterations = 20,
            )
            LottieAnimation(
                composition = outsideAnimSpec1.value,
                progress = { progress1 },
                modifier = Modifier
                    .size(40.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "You will receive a call from your AI Assistant to confirm your requirements.",
                color = textColorDark,
                fontFamily = getFontFamily(weight = FontWeight.SemiBold),
                fontSize = 14.sp, textAlign = TextAlign.Center,
                lineHeight = 20.sp, modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(Modifier.height(80.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(20.dp)
            ) {
                Column {
                    val inlineContent = mapOf(
                        "mySvgImage" to InlineTextContent(
                            placeholder = Placeholder(
                                width = 96.sp,
                                height = 24.sp,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.Bottom
                            )
                        ) {
                            // The composable to render for the placeholder
                            Image(
                                painter = painterResource(id = R.drawable.ic_ai_assistant_text),
                                contentDescription = "My SVG Icon",
                                contentScale = ContentScale.FillHeight // Adjust contentScale as needed
                            )
                        }
                    )
                    Text(
                        buildAnnotatedString {
                            append("Your ")
                            appendInlineContent(id = "mySvgImage")
                            append(" will assist you with")
                        },
                        inlineContent = inlineContent,
                        color = textColorLight,
                        fontSize = 14.sp,
                        fontFamily = getFontFamily(weight = FontWeight.SemiBold)
                    )

                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(R.drawable.ic_tick_green), contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Calling sellers on your behalf & checking availability",
                            color = textColorLight,
                            fontSize = 14.sp,
                            fontFamily = getFontFamily(weight = FontWeight.Normal))
                    }
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(R.drawable.ic_tick_green), contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Connecting with Top Agents on your request",
                            color = textColorLight,
                            fontSize = 14.sp,
                            fontFamily = getFontFamily(weight = FontWeight.Normal))
                    }
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(R.drawable.ic_tick_green), contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Sharing personalised recommendations",
                            color = textColorLight,
                            fontSize = 14.sp,
                            fontFamily = getFontFamily(weight = FontWeight.Normal))
                    }
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(R.drawable.ic_tick_green), contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Creating notes for projects you like",
                            color = textColorLight,
                            fontSize = 14.sp,
                            fontFamily = getFontFamily(weight = FontWeight.Normal))
                    }
                }
            }
        }

        // bottom section
        Box(
            Modifier
                .fillMaxWidth()
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .align(Alignment.BottomCenter)
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .padding(16.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = mbRed, shape = RoundedCornerShape(50))
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Continue", color = Color.White, fontSize = 16.sp, fontFamily = getFontFamily(weight = FontWeight.SemiBold))
            }
        }

    }
}
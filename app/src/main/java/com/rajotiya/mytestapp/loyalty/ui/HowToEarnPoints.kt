package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily

@Composable
fun HowToEarnPoints(modifier: Modifier = Modifier) {
    Box {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Spacer(Modifier.height(30.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, Color(0xffffccd1)))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xffffebee),
                                Color(0xffffffff)
                            )
                        )
                    )
            ) {
                Text(
                    text = "How to earn more points?",
                    color = Color(0xff303030),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 21.dp, top = 56.dp, bottom = 10.dp)
                )
                TaskItemView()
                Spacer(Modifier.height(20.dp))
                TaskItemView()
                Spacer(Modifier.height(44.dp))
            }

        }
        InsufficientPointsView()
    }
}

@Composable
private fun TaskItemView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                .border(BorderStroke(1.dp, Color(0xffe8e8e8)), shape = RoundedCornerShape(15.dp))
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.mbimageloader_no_image_new),
                    contentDescription = "image",
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .width(75.dp),
                    alignment = Alignment.TopStart
                )
            }
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )
                        ) {
                            append("Go On Property Site Visits ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                            )
                        ) {
                            append("with Magicbrick")
                        }
                    },
                    color = Color(0xff303030),
                    modifier = Modifier.padding(start = 16.dp, end = 80.dp)
                )
                LoyaltyPointsView(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 6.dp)
                        .background(color = Color(0xfffff7e1), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 7.dp, vertical = 3.dp),
                    textBeforeIcon = "Earn Up to",
                    textAfterIcon = "50,000 points",
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

                Text(
                    text = "You get:",
                    color = Color(0xff000000),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 17.dp, top = 14.dp)
                )
                Column(modifier = Modifier.padding(horizontal = 17.dp)) {
                    Spacer(Modifier.height(7.dp))
                    ImageTextView(R.drawable.ic_prime_bitmap_chat, "Free Cab Pickup & Drop Service")
                    Spacer(Modifier.height(7.dp))
                    ImageTextView(R.drawable.ic_prime_bitmap_chat, "Free Cab Pickup & Drop Service")
                    Spacer(Modifier.height(7.dp))
                    ImageTextView(R.drawable.ic_prime_bitmap_chat, "Free Cab Pickup & Drop Service")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 26.dp, end = 16.dp, top = 11.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "View Details",
                        color = Color(0xff000000),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                    BottomRedButton(enable = true, ctaText = "Shortlist Projects", onClick = {})
                }
            }
        }
    }
}

@Composable
private fun ImageTextView(image: Int, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(image), contentDescription = null, modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text, fontSize = 12.sp, color = Color(0xff000000),
        )
    }
}

@Composable
private fun InsufficientPointsView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                BorderStroke(2.dp, Color(0xffd8232a)),
                shape = RoundedCornerShape(8.dp)
            )
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = R.drawable.loyality_insufficient_point,
            contentDescription = "gif",
            modifier = Modifier.size(35.dp)
        )
        Column(Modifier.padding(start = 12.dp)) {
            Text(
                text = "Insufficient Points",
                color = Color(0xffd8232a),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = buildAnnotatedString {
                    append("You need to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Earn 9,500 more points ")
                    }
                    append("to redeem this reward")
                },
                color = Color(0xff303030),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}


@Composable
private fun BottomRedButton(modifier: Modifier = Modifier, enable: Boolean, ctaText: String, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clickable { if (enable) onClick() }
            .background(color = Color(0xffd8232a), shape = RoundedCornerShape(22.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ctaText,
                color = if (enable) Color.White else Color.White,
                fontSize = 14.sp,
            )
        }
    }
}


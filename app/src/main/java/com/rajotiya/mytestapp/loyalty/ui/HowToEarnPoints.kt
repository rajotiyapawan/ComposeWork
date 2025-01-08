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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.loyalty.models.InsufficientData
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTaskItem
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTasks
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getComposeImageFromUrl
import com.rajotiya.mytestapp.utility.getFontFamily

@Composable
fun HowToEarnPoints(modifier: Modifier = Modifier, headers: InsufficientData?, tasks: LoyaltyTasks?) {
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
                    text = tasks?.title ?: "",
                    color = Color(0xff303030),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 21.dp, top = 56.dp, bottom = 10.dp)
                )
                LazyColumn(
                    modifier
                        .fillMaxWidth()
                        .padding(bottom = 44.dp)
                        .heightIn(max = 1500.dp)
                        .padding(horizontal = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    tasks?.items?.let {
                        items(it) { task ->
                            TaskItemView(modifier = Modifier.fillMaxWidth(), task, onViewDetailClick = {  }) {

                            }
                        }
                    }
                }
            }

        }
        InsufficientPointsView(title = headers?.title ?: "", pointsToEarn = headers?.pointsToEarn ?: "")
    }
}

@Composable
private fun TaskItemView(modifier: Modifier, task: LoyaltyTaskItem, onViewDetailClick:()->Unit ,onTaskClick:()->Unit) {
    Card(
        modifier = modifier
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
                    text = task.title ?: "",
                    color = Color(0xff303030),
                    modifier = Modifier.padding(start = 16.dp, end = 80.dp)
                )
                LoyaltyPointsView(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 6.dp)
                        .background(color = Color(0xfffff7e1), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 7.dp, vertical = 3.dp),
                    textBeforeIcon = "Earn Up to",
                    textAfterIcon = "${task.points} points",
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


                task.benefits?.let { benefit ->
                    Text(
                        text = benefit.title ?: "",
                        color = Color(0xff000000),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 17.dp, top = 14.dp)
                    )
                    Column(modifier = Modifier.padding(horizontal = 17.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                        benefit.list?.let {
                            it.forEach { benefitItem ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = getComposeImageFromUrl(benefitItem.imgUrl),
                                        contentDescription = null,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        benefitItem.text ?: "",
                                        fontSize = 12.sp,
                                        color = Color(0xff000000),
                                        fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
                                    )
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 26.dp, end = 16.dp, top = 11.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.noRippleClick { onViewDetailClick() }) {
                        Text(
                            text = "View Details",
                            color = Color(0xff000000),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(color = mbRed, shape = RoundedCornerShape(50))
                            .noRippleClick { onTaskClick() }
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        Text(
                            task.cta ?: "",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InsufficientPointsView(title: String, pointsToEarn: String) {
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
                text = title,
                color = Color(0xffd8232a),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = buildAnnotatedString {
                    append("You need to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Earn $pointsToEarn more points ")
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


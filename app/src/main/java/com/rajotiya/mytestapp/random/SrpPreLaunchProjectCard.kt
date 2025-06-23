package com.rajotiya.mytestapp.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
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

/**
 * Created by Pawan Rajotiya on 08-05-2025.
 */

@Composable
fun SrpPreLaunchProjectCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(360.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.vector_city_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.45f)
                    .background(color = Color.Gray),
                contentScale = ContentScale.Fit
            )
            Column(Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 10.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        buildAnnotatedString {
                            append("\u20b9 1.4 Cr to 2.8 Cr ")
                            withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR))) {
                                append("(expected)")
                            }
                        },
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        color = textColorDark,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                    )
                    Spacer(Modifier.width(7.dp))
                    Text(
                        " | 2, 3 BHK",
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        color = textColorDark
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(Icons.Default.Share, contentDescription = null)
                }
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD))) {
                            append("Upcoming Project by Tata Housing ")
                        }
                        append("in Sarjapura, Bangalore")
                    },
                    fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                    color = textColorLight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
                Text(
                    "Expected Possession by: 2028",
                    fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR),
                    color = textColorLight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
                Spacer(Modifier.height(20.dp))
                Row {
                    Box(
                        Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(width = 1.dp, color = mbRed, shape = CircleShape)
                            .padding(5.dp), contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = null, modifier = Modifier.size(19.dp))
                    }
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .border(width = 1.dp, color = mbRed, shape = RoundedCornerShape(50)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Notify Me",
                                color = mbRed,
                                fontSize = 14.sp,
                                lineHeight = 30.sp,
                                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                            )
                        }
                        Text(
                            "Upon RERA Approval",
                            fontSize = 11.sp,
                            lineHeight = 15.sp,
                            color = textColorLight,
                            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .background(color = mbRed, shape = RoundedCornerShape(50)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Request Callback",
                                color = Color.White,
                                fontSize = 14.sp,
                                lineHeight = 30.sp,
                                fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                            )
                        }
                        Text(
                            "On Project Launch",
                            fontSize = 11.sp,
                            lineHeight = 15.sp,
                            color = textColorLight,
                            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                        )
                    }
                }
                Spacer(Modifier.height(17.dp))
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontFamily = getFontFamily(Constants.MONTSERRAT_BOLD))) {
                        append("Note: ")
                    }
                    append("This information is for reference only and does not constitute an advertisement or an invitation to invest")
                }, fontSize = 12.sp, color = textColorLight, fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR))
            }
        }
    }
}

@Preview
@Composable
private fun TestSrpCard() {
    Column(Modifier.padding(16.dp)) {
        SrpPreLaunchProjectCard(Modifier.fillMaxWidth())
    }
}
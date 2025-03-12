package com.rajotiya.mytestapp.sitevisit_flow.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitFlowActivity
import com.rajotiya.mytestapp.sitevisit_flow.SiteVisitScreens
import com.rajotiya.mytestapp.sitevisit_flow.SvFlowUserEvents
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily
import com.rajotiya.mytestapp.utility.noRippleClick

@Composable
fun FreeCabIntroScreen(modifier: Modifier = Modifier) {
    val viewModel = SiteVisitFlowActivity.LocalSiteVisitFlowViewModel.current
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.site_visit_cab),
            contentDescription = "Taxi Image",
            modifier = Modifier
                .size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = buildAnnotatedString {
                append("Now, you can book ")
                withStyle(style = SpanStyle(color = Color(0xff009681), fontWeight = FontWeight.Bold)) {
                    append("Free Cab")
                }
                append(" for site visits with us")
            },
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            listOf(
                "It's absolutely Free - no hidden charges!",
                "Visit multiple projects in one trip",
                "Keep the cab & driver for your entire trip"
            ).forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Check",
                        tint = Color(0xff009681),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = text, fontSize = 16.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 0.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xffffffff))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Do you want ")
                        withStyle(style = SpanStyle(color = Color(0xff303030), fontWeight = FontWeight.Bold)) {
                            append("Free Cab")
                        }
                        append(" for visiting this property?")
                    },
                    fontSize = 16.sp, lineHeight = 22.sp, color = textColorDark,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = mbRed, shape = RoundedCornerShape(50))
                        .padding(vertical = 6.dp)
                        .noRippleClick {
                            viewModel.sendUserEvent(
                                SvFlowUserEvents.NavigateTo(
                                    route = SiteVisitScreens.SvDateTimeSelection.name, saveToBackStack = false,
                                    currentScreen = SiteVisitScreens.FreeCabIntro.name
                                )
                            )
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Yes",
                        color = Color.White,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(Modifier.noRippleClick {
                    viewModel.sendUserEvent(SvFlowUserEvents.DenyFreeCab)
                }) {
                    Text(
                        text = "No, I don’t want Free Cab",
                        color = Color(0xff303030),
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }
        }
    }
}
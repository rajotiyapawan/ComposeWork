package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark

/**
 * Created by Pawan Rajotiya on 14-02-2025.
 */


@Composable
fun LoyaltyTpSvDialog(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(22.dp))
            .padding(10.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(R.drawable.ic_unselected_two), contentDescription = null)
            Text(text = buildAnnotatedString {
                append("You shortlisted 2 Project")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(" for site visit.")
                }
            }, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color(0xff303030))
        }
        Spacer(Modifier.height(18.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(11.dp))
                    .padding(top = 70.dp, start = 12.dp, end = 12.dp, bottom = 15.dp)
            ) {
                Column {
                    Row(Modifier.padding(horizontal = 15.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.loyalty_coin),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            "15,000 points reserved",
                            modifier = Modifier.padding(horizontal = 15.dp),
                            color = textColorDark,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Text(
                        "Complete site visit to claim these points",
                        color = textColorDark,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 14.dp)
                            .border(width = 1.dp, color = mbRed, shape = RoundedCornerShape(50))
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Go to Homepage", color = mbRed, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .border(width = 1.dp, color = Color(0xffd9d9d9), shape = RoundedCornerShape(6.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(6.dp))
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            ) {
                Text(text = buildAnnotatedString {
                    append("You will shortly receive a call")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append(" from our executive to assist in scheduling your site visit.")
                    }
                }, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color(0xff303030), textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestTpSvDialog(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .background(color = Color.Gray)
            .padding(20.dp)
    ) {
        LoyaltyTpSvDialog()
    }
}
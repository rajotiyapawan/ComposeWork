package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick

/**
 * Created by Pawan Rajotiya on 20-12-2024.
 */

@Composable
fun StartVerificationView(modifier: Modifier, onClick:()->Unit) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    brush = Brush.linearGradient(
                        colors = listOf(Color.White, Color(0xffFFF5CC)),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                )
                .padding(top = 40.dp, bottom = 8.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Great!", fontSize = 34.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = buildAnnotatedString {
                append("Just one step to activate\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append("Free <Crown> MB PRIME")
                }
            }, fontSize = 16.sp)
        }
        Column(
            modifier = Modifier
                .padding(top = 30.dp, bottom = 18.dp)
                .padding(horizontal = 18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xff009681),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(14.dp)
                )
                Text(text = "App downloaded", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, lineHeight = 14.sp)
            }
            VerticalDivider(
                thickness = 2.dp,
                color = Color(0xff009681),
                modifier = Modifier
                    .height(21.dp)
                    .offset(y = (-3).dp)
                    .padding(start = 6.dp)
            )
            Row(modifier = Modifier.offset(y = (-3.5).dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(14.dp)
                        .clip(CircleShape)
                        .border(width = 1.dp, color = Color(0xffd7d7d7), shape = CircleShape), contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(color = Color(0xffd7d7d7))
                    )
                }
                Text(text = buildAnnotatedString {
                    append("Verify yourself ")
                    withStyle(style = SpanStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal)) {
                        append("powered by")
                    }
                }, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
                    .noRippleClick { onClick() }
                    .background(color = Color.Red, shape = RoundedCornerShape(50))
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Start Verification", color = Color.White, fontSize = 16.sp)
                Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = null, tint = Color.White)
            }
        }
    }
}

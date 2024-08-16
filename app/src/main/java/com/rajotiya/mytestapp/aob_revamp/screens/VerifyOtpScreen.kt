package com.rajotiya.mytestapp.aob_revamp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rajotiya.mytestapp.aob_revamp.AOBViewModel
import com.rajotiya.mytestapp.aob_revamp.AppOnBoardingComposeActivity
import com.rajotiya.mytestapp.aob_revamp.ui.theme.disableButtonColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.otpContainerColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.utils.PIN_VIEW_TYPE_BORDER
import com.rajotiya.mytestapp.aob_revamp.utils.PinView

/**
 * Created by Pawan Rajotiya on 07-08-2024.
 */

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VerifyOtpScreen(
    modifier: Modifier = Modifier,
    viewModel: AOBViewModel = AppOnBoardingComposeActivity.localViewModelCompositionLocal.current,
    onSelection: (id: Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(start = 20.dp, end = 16.dp, top = 12.dp, bottom = 44.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null, tint = Color.White)
                Box(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(text = "Skip", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Verify your mobile number",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-20).dp)
                .zIndex(8F)
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .padding(start = 20.dp, end = 20.dp, top = 36.dp)
        ) {
            val heading = buildAnnotatedString {
                append("One Time Password (OTP) has been sent to ")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("+91 - 927398267297")
                }
            }
            Text(
                text = heading,
                fontSize = 16.sp,
                color = textColorDark,
                lineHeight = 24.sp
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Enter OTP", color = textColorLight, fontSize = 14.sp, lineHeight = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            val (otp, otpChanged) = remember { mutableStateOf("") }
            PinView(pinText = otp, onPinTextChange = otpChanged, type = PIN_VIEW_TYPE_BORDER, containerColor = otpContainerColor)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "You’ll receive OTP via SMS within 00:43 min")
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = disableButtonColor, shape = RoundedCornerShape(50))
            ) {
                Text(
                    text = "Verify", modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp), textAlign = TextAlign.Center
                )
            }
        }
    }
}
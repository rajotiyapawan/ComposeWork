package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Pawan Rajotiya on 20-12-2024.
 */

@Composable
fun ExperianUserDetailsView(modifier: Modifier = Modifier) {
    val (value, onValueChange) = remember { mutableStateOf("") }
    Column(modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 30.dp, end = 30.dp)
        ) {
            Text(text = "Verify with experian", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Row {
                CustomTextField(
                    label = "First Name",
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                CustomTextField(
                    label = "Last Name",
                    value = value,
                    onValueChange = onValueChange, modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                label = "Email Address",
                value = value,
                onValueChange = onValueChange, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                label = "Phone Number",
                value = value,
                onValueChange = onValueChange, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Checkbox(
                    checked = true, onCheckedChange = {}, colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xff009681)
                    )
                )
                Text(
                    text = "By clicking the Get Free Report you hereby appoint Magicbricks as your authorized representative to receive your credit information from Experian. You hereby consent to such credit information being provided by Experian at your registered email id and also through your Magicbricks account as per your independent registration with Magicbricks subject to Terms and Conditions",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xff303030)
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Red, shape = RoundedCornerShape(50))
                    .padding(vertical = 8.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = "Get Verification Code", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
        }

    }
}

@Composable
fun CustomTextField(modifier: Modifier, value: String, onValueChange: (String) -> Unit, label: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }

    val labelPadding by animateDpAsState(
        label = "topPaddingAnimation",
        targetValue = if (isFocused || text.text.isNotEmpty()) 10.dp else 0.dp
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 16.dp), verticalArrangement = Arrangement.Bottom
    ) {
        // Label
        Text(
            text = label,
            color = Color(0xff303030),
            fontSize = if (isFocused) 11.sp else 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 0.dp, bottom = labelPadding)
        )

        // TextField
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            cursorBrush = SolidColor(Color.Black),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                color = Color(0xff303030),
                fontWeight = FontWeight.SemiBold
            ),
            decorationBox = { innerTextField ->
                Column {
                    innerTextField() // Actual text input
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color(0xFFD7D7D7)) // Underline
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
        )
    }
}

@Composable
fun ExperainOtpView(modifier: Modifier = Modifier) {
    Column(modifier.background(color = Color(0xfff5f5f5))) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 30.dp, end = 30.dp)
        ) {
            Text(text = "Verify with experian", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))
                .padding(horizontal = 20.dp, vertical = 20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter Verification Code sent on +91XXXXX 66164",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xff303030),
                modifier = Modifier.padding(horizontal = 30.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(15.dp))
            Spacer(modifier = Modifier.height(42.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(15.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = "Didn't get the verification code?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff303030),
                    lineHeight = 30.sp
                )
                Row {
                    Box(
                        modifier = Modifier
                            .border(width = 1.dp, color = Color(0xff5d5d5d), shape = RoundedCornerShape(50))
                            .padding(vertical = 8.dp)
                            .weight(1f), contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Resend in 45 Sec", color = Color(0xff5d5d5d))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(
                        modifier = Modifier
                            .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(50))
                            .padding(vertical = 8.dp)
                            .weight(1f), contentAlignment = Alignment.Center
                    )
                    {
                        Text("Get code on call", color = Color.Red)
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = Color.Red, shape = RoundedCornerShape(50))
                        .padding(vertical = 9.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Verify", color = Color.White)
                }
            }
        }
    }
}
package com.rajotiya.mytestapp.req_capture

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.utils.CustomMobileInputField
import com.rajotiya.mytestapp.aob_revamp.utils.CustomTextField

/**
 * Created by Pawan Rajotiya on 06-05-2026.
 */

@Composable
fun RequirementCaptureFlowRoot(
    modifier: Modifier = Modifier,
    viewModel: RequirementCaptureFlowVM
) {
    val state = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                RequirementCaptureFlowEffect.CloseDialog -> {}
                is RequirementCaptureFlowEffect.ShowToast -> {
                    // show toast/snackbar
                }
            }
        }
    }

    Column(
        modifier
    ) {
        TopView(Modifier.fillMaxWidth())

        Column(
            Modifier
                .fillMaxWidth()
                .offset(y = (-20).dp)
                .zIndex(8F)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)
                )
                .padding(start = 20.dp, end = 20.dp, top = 36.dp)
        ) {

            when (state.screen) {
                RequirementCaptureFlowScreen.FORM -> FormScreen(Modifier.fillMaxWidth(), state, viewModel::processIntent)
                RequirementCaptureFlowScreen.OTP -> OtpScreen(Modifier.fillMaxWidth(), state, viewModel::processIntent)
            }
        }
    }
}

@Composable
private fun TopView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(color = mbRed)
            .padding(start = 20.dp, end = 16.dp, top = 12.dp, bottom = 44.dp)
    ) {
        Text("Let’s save your requirements", color = Color.White)
    }
}

@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    state: RequirementCaptureFlowState,
    sendIntent: (RequirementCaptureFlowIntent) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.name.text,
            onValueChange = {
                Log.d("NameTextField", "New value: $it")
                sendIntent(RequirementCaptureFlowIntent.NameChanged(TextFieldValue(it.filter { char -> char.isLetter() || char.isWhitespace() })))
            },
            label = "Name",
            singleLine = true,
            errorMessage = when (0) {
                1 -> "Please Enter your Name"
                2 -> "Name should have min 3 alphabets"
                else -> ""
            },
            isError = false,
        )
        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email.text,
            onValueChange = {
                sendIntent(RequirementCaptureFlowIntent.EmailChanged(TextFieldValue(it)))
            },
            label = "Email",
            singleLine = true,
            isError = false,
            errorMessage = when (0) {
                1 -> "Please Enter your Email"
                2 -> "Please Enter a Valid Email"
                3 -> "Use a valid domain name"

                else -> ""
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.height(24.dp))

        CustomMobileInputField(
            modifier = Modifier.fillMaxWidth(),
            isd = state.isd,
            isdChange = {
                sendIntent(RequirementCaptureFlowIntent.IsdChanged(it))
            },
            mobile = state.phone.text,
            mobileChange = {
                sendIntent(RequirementCaptureFlowIntent.PhoneChanged(TextFieldValue(it)))
            },
            label = "Phone Number",
            isError = false,
            errorMessage = if (true) "Please Enter a Mobile Number" else "Please Enter a Valid Mobile Number"
        ) { // get the complete country code and value here
            sendIntent(RequirementCaptureFlowIntent.IsdChanged(it.code ?: "50"))
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { sendIntent(RequirementCaptureFlowIntent.SubmitForm) }
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    state: RequirementCaptureFlowState,
    sendIntent: (RequirementCaptureFlowIntent) -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {

        Text("Enter OTP")

        Spacer(Modifier.height(12.dp))

        TextField(
            value = state.otp,
            onValueChange = {
                sendIntent(RequirementCaptureFlowIntent.OtpChanged(it))
            },
            label = { Text("OTP") }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { sendIntent(RequirementCaptureFlowIntent.VerifyOtp) }
        ) {
            Text("Verify")
        }

        Spacer(Modifier.height(8.dp))

        Text(
            "Back",
            modifier = Modifier.clickable {
                sendIntent(RequirementCaptureFlowIntent.BackClicked)
            }
        )
    }
}

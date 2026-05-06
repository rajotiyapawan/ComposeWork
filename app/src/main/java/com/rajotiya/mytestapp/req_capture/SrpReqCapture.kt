package com.rajotiya.mytestapp.req_capture

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.utils.BottomRedButton
import com.rajotiya.mytestapp.aob_revamp.utils.CustomMobileInputField
import com.rajotiya.mytestapp.aob_revamp.utils.CustomTextField
import com.rajotiya.mytestapp.utility.checkValueOfIsdCode

/**
 * Created by Pawan Rajotiya on 06-05-2026.
 */

@Composable
fun RequirementCaptureFlowRoot(
    modifier: Modifier = Modifier,
    viewModel: RequirementCaptureFlowVM
) {
    val state by viewModel.uiState.collectAsState()
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                RequirementCaptureFlowEffect.CloseDialog -> {}
                is RequirementCaptureFlowEffect.ShowToast -> {
                    Log.d("Toast", "Name: ${state.name} | Email: ${state.email} | Phone: ${state.isd} ${state.phone}")
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier.fillMaxHeight().background(Color.White)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                localFocusManager.clearFocus()
            }
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
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            BottomRedButton(enable = true, ctaText = "Next", onClick = {
                viewModel.processIntent(RequirementCaptureFlowIntent.SubmitForm)
            })
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
    val (name, nameChange) = remember { mutableStateOf(state.name) }
    val (email, emailChange) = remember { mutableStateOf(state.email) }
    val (mobile, mobileChange) = remember { mutableStateOf(state.phone) }
    val (isd, isdChange) = remember { mutableStateOf(checkValueOfIsdCode(state.isd.ifEmpty { "50" }.toInt())) }

    Column(
        modifier = modifier
    ) {
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                Log.d("NameTextField", "New value: $it")
                nameChange(it.filter { char -> char.isLetter() || char.isWhitespace() })
                sendIntent(RequirementCaptureFlowIntent.NameChanged(it.filter { char -> char.isLetter() || char.isWhitespace() }))
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
            value = email,
            onValueChange = {
                Log.d("EmailTextField", "New value: $it")
                emailChange(it)
                sendIntent(RequirementCaptureFlowIntent.EmailChanged(it))
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

        CustomMobileInputField(
            modifier = Modifier.fillMaxWidth(),
            isd = isd,
            isdChange = {
                isdChange(it)
            },
            mobile = mobile,
            mobileChange = {
                mobileChange(it)
                sendIntent(RequirementCaptureFlowIntent.PhoneChanged(it))
            },
            label = "Phone Number",
            isError = false,
            errorMessage = if (true) "Please Enter a Mobile Number" else "Please Enter a Valid Mobile Number"
        ) { // get the complete country code and value here
            sendIntent(RequirementCaptureFlowIntent.IsdChanged(it.code ?: "50"))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "I agree to Magicbricks’ Term of use", color = textColorExtraLight, fontSize = 12.sp, lineHeight = 20.sp)
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

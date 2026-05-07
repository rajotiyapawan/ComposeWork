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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.otpContainerColor
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorExtraLight
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.aob_revamp.utils.BottomRedButton
import com.rajotiya.mytestapp.aob_revamp.utils.CustomMobileInputField
import com.rajotiya.mytestapp.aob_revamp.utils.CustomTextField
import com.rajotiya.mytestapp.aob_revamp.utils.PIN_VIEW_TYPE_BORDER
import com.rajotiya.mytestapp.aob_revamp.utils.PinView
import com.rajotiya.mytestapp.req_capture.ui.theme.RequirementCaptureTheme
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.checkValueOfIsdCode
import com.rajotiya.mytestapp.utility.getFont
import kotlinx.coroutines.delay
import java.util.Locale

/**
 * Created by Pawan Rajotiya on 06-05-2026.
 */

@Composable
fun RequirementCaptureFlowRoot(
    modifier: Modifier = Modifier, viewModel: RequirementCaptureFlowVM
) {
    RequirementCaptureTheme(darkTheme = false) {
        val state by viewModel.uiState.collectAsState()
        val localFocusManager = LocalFocusManager.current
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    RequirementCaptureFlowEffect.CloseDialog -> {}
                    is RequirementCaptureFlowEffect.ShowToast -> {
                        Log.d("Toast", "Name: ${state.name} | Email: ${state.email} | Mobile: ${state.isd} ${state.mobile}")
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        Column(
            modifier
                .fillMaxHeight()
                .background(Color.White)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() }, indication = null
                ) {
                    localFocusManager.clearFocus()
                }) {
            TopView(Modifier.fillMaxWidth())

            Column(
                Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp)
                    .zIndex(8F)
                    .background(
                        color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)
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
                        color = Color.White, shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                val ctaText = if (state.screen == RequirementCaptureFlowScreen.FORM) "Next" else "Verify"
                BottomRedButton(enable = true, ctaText = ctaText, onClick = {
                    if (state.screen == RequirementCaptureFlowScreen.FORM) {
                        viewModel.processIntent(RequirementCaptureFlowIntent.SubmitForm)
                    } else {
                        viewModel.processIntent(RequirementCaptureFlowIntent.VerifyOtp)
                    }
                })
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
        Text("Let’s save your requirements", color = Color.White, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun FormScreen(
    modifier: Modifier = Modifier, state: RequirementCaptureFlowState, sendIntent: (RequirementCaptureFlowIntent) -> Unit
) {
    val (name, nameChange) = remember { mutableStateOf(state.name) }
    val (email, emailChange) = remember { mutableStateOf(state.email) }
    val (mobile, mobileChange) = remember { mutableStateOf(state.mobile) }
    val (isd, isdChange) = remember { mutableStateOf(checkValueOfIsdCode(state.isd.ifEmpty { "50" }.toInt())) }

    // Error handling
    var showNameError by remember { mutableIntStateOf(0) }
    var showEmailError by remember { mutableIntStateOf(0) }
    var showMobileError by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = state) {
        if (state.showError) {
            showNameError = state.nameError
            showEmailError = state.emailError
            showMobileError = state.mobileError
        }
    }

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
            errorMessage = when (showNameError) {
                1 -> "Please Enter your Name"
                2 -> "Name should have min 3 alphabets"
                else -> ""
            },
            isError = showNameError > 0,
        )
        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(), value = email, onValueChange = {
                Log.d("EmailTextField", "New value: $it")
                emailChange(it)
                sendIntent(RequirementCaptureFlowIntent.EmailChanged(it))
            }, label = "Email", singleLine = true, isError = showEmailError > 0, errorMessage = when (showEmailError) {
                1 -> "Please Enter your Email"
                2 -> "Please Enter a Valid Email"
                3 -> "Use a valid domain name"

                else -> ""
            }, keyboardOptions = KeyboardOptions(
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
            label = "Mobile Number",
            isError = showMobileError > 0,
            errorMessage = if (showMobileError == 1) "Please Enter a Mobile Number" else "Please Enter a Valid Mobile Number"
        ) { // get the complete country code and value here
            sendIntent(RequirementCaptureFlowIntent.IsdChanged(it.code ?: "50"))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "I agree to Magicbricks’ Term of use", color = textColorExtraLight, fontSize = 12.sp, lineHeight = 20.sp)
    }
}

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier, state: RequirementCaptureFlowState, sendIntent: (RequirementCaptureFlowIntent) -> Unit
) {
    val (otp, otpChanged) = remember { mutableStateOf("") }
    var timerFinish by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableIntStateOf(60) }
    val minutes = timeLeft / 60
    val seconds = timeLeft % 60

    // Start the timer when the screen is displayed
    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
        timerFinish = true
    }

    // Clean up when the composable leaves the composition
    DisposableEffect(Unit) {
        onDispose {
            timeLeft = 0 // Stops the timer when leaving the screen
        }
    }

    Column(modifier = modifier) {

        val heading = buildAnnotatedString {
            append("One Time Password (OTP) has been sent to ")
            withStyle(SpanStyle(fontFamily = FontFamily(getFont(Constants.MONTSERRAT_SEMIBOLD)))) {
                append("${checkValueOfIsdCode(state.isd.toInt())} - " + state.mobile + " ")
            }
            withLink(
                LinkAnnotation.Clickable(
                    tag = "EDIT",
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = mbRed,
                            textDecoration = TextDecoration.None
                        )
                    ),
                    linkInteractionListener = {
                        sendIntent(RequirementCaptureFlowIntent.EditClicked)
                    })
            ) {
                append("Edit")
            }
        }
        Text(
            text = heading,
            style = TextStyle(
                fontSize = 16.sp,
                color = textColorDark,
                lineHeight = 24.sp,
                fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
            ),
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Enter OTP", color = textColorLight, fontSize = 14.sp, lineHeight = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        PinView(
            pinText = otp,
            onPinTextChange = {
                sendIntent(RequirementCaptureFlowIntent.OtpChanged(it))
                otpChanged(it)
                              },
            digitCount = 4,
            type = PIN_VIEW_TYPE_BORDER,
            containerColor = otpContainerColor
        )

        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (!timerFinish) {
                Text(
                    text = buildAnnotatedString {
                        append("You'll receive OTP within ")
                        withStyle(style = SpanStyle(color = mbRed)) {
                            append(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds))
                        }
                        append(" min")
                    },
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = textColorDark,
                    fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
                )
            } else {
                val annotatedString = buildAnnotatedString {
                    append("Didn't receive OTP? ")
                    pushStringAnnotation(tag = "RESEND", annotation = "")
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "RESEND", styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = mbRed,
                                    textDecoration = TextDecoration.None
                                )
                            ),
                            linkInteractionListener = {
                                sendIntent(RequirementCaptureFlowIntent.EditClicked)
                            })
                    ) {
                        append("Resend")
                    }
                }
                Text(
                    text = annotatedString,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        color = textColorDark,
                        fontFamily = FontFamily(getFont(Constants.MONTSERRAT_REGULAR))
                    ),
                )
            }
        }
    }
}

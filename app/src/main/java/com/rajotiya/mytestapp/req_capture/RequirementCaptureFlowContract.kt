package com.rajotiya.mytestapp.req_capture

import androidx.compose.ui.text.input.TextFieldValue
import com.rajotiya.mytestapp.base.UiEffect
import com.rajotiya.mytestapp.base.UiIntent

/**
 * Created by Pawan Rajotiya on 05-05-2026.
 */

enum class RequirementCaptureFlowScreen { FORM, OTP }

data class RequirementCaptureFlowState(
    val screen: RequirementCaptureFlowScreen = RequirementCaptureFlowScreen.FORM,
    val name: String = "",
    val nameError: Int = 1,
    val mobile: String = "",
    val mobileError: Int = 1,
    val email: String = "",
    val emailError: Int = 1,
    val isd: String = "50",
    val otp: String = "",
    val isLoading: Boolean = false,
    val showError: Boolean = false,
)

sealed interface RequirementCaptureFlowIntent : UiIntent {
    // FORM Screen
    data class NameChanged(val value: String) : RequirementCaptureFlowIntent
    data class PhoneChanged(val value: String) : RequirementCaptureFlowIntent
    data class IsdChanged(val value: String) : RequirementCaptureFlowIntent
    data class EmailChanged(val value: String) : RequirementCaptureFlowIntent

    object SubmitForm : RequirementCaptureFlowIntent

    // OTP Screen
    object EditClicked : RequirementCaptureFlowIntent
    object ResendClicked : RequirementCaptureFlowIntent
    data class OtpChanged(val value: String) : RequirementCaptureFlowIntent
    object VerifyOtp : RequirementCaptureFlowIntent

    object BackClicked : RequirementCaptureFlowIntent
}

sealed interface RequirementCaptureFlowEffect : UiEffect {
    object CloseDialog : RequirementCaptureFlowEffect
    data class ShowToast(val message: String) : RequirementCaptureFlowEffect
}

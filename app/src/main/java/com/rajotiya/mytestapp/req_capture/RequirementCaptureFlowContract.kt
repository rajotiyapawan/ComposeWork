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
    val phone: String = "",
    val email: String = "",
    val isd: String = "",
    val otp: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface RequirementCaptureFlowIntent : UiIntent {
    data class NameChanged(val value: String) : RequirementCaptureFlowIntent
    data class PhoneChanged(val value: String) : RequirementCaptureFlowIntent
    data class IsdChanged(val value: String) : RequirementCaptureFlowIntent
    data class EmailChanged(val value: String) : RequirementCaptureFlowIntent

    object SubmitForm : RequirementCaptureFlowIntent

    data class OtpChanged(val value: String) : RequirementCaptureFlowIntent
    object VerifyOtp : RequirementCaptureFlowIntent

    object BackClicked : RequirementCaptureFlowIntent
}

sealed interface RequirementCaptureFlowEffect : UiEffect {
    object CloseDialog : RequirementCaptureFlowEffect
    data class ShowToast(val message: String) : RequirementCaptureFlowEffect
}

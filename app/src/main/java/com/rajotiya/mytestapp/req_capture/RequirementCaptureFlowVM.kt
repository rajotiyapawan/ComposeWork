package com.rajotiya.mytestapp.req_capture

import com.rajotiya.mytestapp.base.BaseViewModelMVI
import kotlinx.coroutines.delay

/**
 * Created by Pawan Rajotiya on 05-05-2026.
 */
class RequirementCaptureFlowVM :
    BaseViewModelMVI<RequirementCaptureFlowState, RequirementCaptureFlowIntent, RequirementCaptureFlowEffect>(
        RequirementCaptureFlowState()
    ) {

    override suspend fun handleIntent(intent: RequirementCaptureFlowIntent) {
        when (intent) {

            is RequirementCaptureFlowIntent.NameChanged -> {
                setState { copy(name = intent.value) }
            }

            is RequirementCaptureFlowIntent.PhoneChanged -> {
                setState { copy(phone = intent.value) }
            }

            is RequirementCaptureFlowIntent.IsdChanged -> {
                setState { copy(isd = intent.value) }
            }

            is RequirementCaptureFlowIntent.EmailChanged -> {
                setState { copy(email = intent.value) }
            }

            RequirementCaptureFlowIntent.SubmitForm -> {
                submitForm()
            }

            is RequirementCaptureFlowIntent.OtpChanged -> {
                setState { copy(otp = intent.value) }
            }

            RequirementCaptureFlowIntent.VerifyOtp -> {
                verifyOtp()
            }

            RequirementCaptureFlowIntent.BackClicked -> {
                handleBack()
            }
        }
    }

    private fun submitForm() {
        val state = uiState.value

        if (state.name.isBlank() || state.phone.isBlank()) {
            sendEffect { RequirementCaptureFlowEffect.ShowToast("Fill required fields") }
            return
        }

        // simulate API
        setState { copy(isLoading = true) }

        launchMain {
            delay(1000)

            setState {
                copy(
                    isLoading = false,
                    screen = RequirementCaptureFlowScreen.OTP
                )
            }
        }
    }

    private fun verifyOtp() {
        val state = uiState.value

        if (state.otp.length < 4) {
            sendEffect { RequirementCaptureFlowEffect.ShowToast("Invalid OTP") }
            return
        }

        setState { copy(isLoading = true) }

        launchMain {
            delay(1000)

            setState { copy(isLoading = false) }
            sendEffect { RequirementCaptureFlowEffect.CloseDialog }
        }
    }

    private fun handleBack() {
        val current = uiState.value.screen
        if (current == RequirementCaptureFlowScreen.OTP) {
            setState { copy(screen = RequirementCaptureFlowScreen.FORM) }
        } else {
            sendEffect { RequirementCaptureFlowEffect.CloseDialog }
        }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
    }
}
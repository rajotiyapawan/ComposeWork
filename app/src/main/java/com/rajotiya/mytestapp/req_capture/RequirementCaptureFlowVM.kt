package com.rajotiya.mytestapp.req_capture

import com.rajotiya.mytestapp.base.BaseViewModelMVI
import com.rajotiya.mytestapp.utility.checkGmailSpelling
import com.rajotiya.mytestapp.utility.checkInValidDomainEmail
import com.rajotiya.mytestapp.utility.isEmailValid
import com.rajotiya.mytestapp.utility.isMobileNumberValid
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
                setState {
                    val name = intent.value
                    val nameError: Int = if (name.isEmpty()) 1 else if (name.length < 3) 2 else 0
                    copy(name = name, nameError = nameError)
                }
            }

            is RequirementCaptureFlowIntent.PhoneChanged -> {
                setState {
                    val mobile = intent.value
                    val isdCode = uiState.value.isd
                    val mobileError: Int =
                        if (mobile.isEmpty()) 1
                        else if (!isMobileNumberValid(
                                mobile,
                                isdCode.equals("50", true)
                            )
                        ) 2
                        else 0
                    copy(mobile = mobile, mobileError = mobileError)
                }
            }

            is RequirementCaptureFlowIntent.IsdChanged -> {
                setState { copy(isd = intent.value) }
            }

            is RequirementCaptureFlowIntent.EmailChanged -> {
                setState {
                    val email = intent.value
                    val emailError: Int =
                        if (email.isEmpty()) 1
                        else if (!isEmailValid(email)) 2
                        else if (!checkInValidDomainEmail(email) || !checkGmailSpelling(
                                email
                            )
                        ) 3
                        else 0
                    copy(email = email, emailError = emailError)
                }
            }

            RequirementCaptureFlowIntent.SubmitForm -> {
                validateAndSubmit()
            }

            is RequirementCaptureFlowIntent.OtpChanged -> {
                setState { copy(otp = intent.value) }
            }

            RequirementCaptureFlowIntent.VerifyOtp -> {
                verifyOtp()
            }

            RequirementCaptureFlowIntent.ResendClicked -> {

            }

            RequirementCaptureFlowIntent.BackClicked -> {
                handleBack()
            }

            RequirementCaptureFlowIntent.EditClicked -> {
                setState {
                    copy(screen = RequirementCaptureFlowScreen.FORM)
                }
            }
        }
    }

    private fun validateAndSubmit() {
        var valid = false
        // validate the form values
        val name = uiState.value.nameError
        val email = uiState.value.emailError
        val mobile = uiState.value.mobileError

        if (name + email + mobile == 0) { // no error -- all validations passed
            valid = true
        }
        // update the errors to state
        setState {
            copy(showError = !valid)
        }
        if (valid || true) submitForm()
    }

    private fun submitForm() {
        val state = uiState.value

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
            sendEffect { RequirementCaptureFlowEffect.ShowToast("Please Enter a valid Otp") }
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
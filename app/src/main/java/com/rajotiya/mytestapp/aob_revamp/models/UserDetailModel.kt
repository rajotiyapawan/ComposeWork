package com.rajotiya.mytestapp.aob_revamp.models

import androidx.annotation.Keep

/** User detail model for keeping track of input values as well as error
 *  Error types -> 0 - No error, 1 - Empty Error, 2 - Invalid Value error, 3 - Invalid Domain name for the email case */

@Keep
data class UserDetailModel(
    var name: String = "",
    val nameError: Int = 1,
    var email: String = "",
    val emailError: Int = 1,
    var mobile: String = "",
    val mobileError: Int = 1,
    var isdCode: String = "50",
    val showError: Boolean = false,
    val flow: String = "default"
)

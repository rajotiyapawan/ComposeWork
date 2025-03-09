package com.rajotiya.mytestapp.utility

data class ComposeUIState<T>(
    val apiState: T? = null,
    var isIdle: Boolean = false
)

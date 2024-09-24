package com.rajotiya.mytestapp.aob_revamp.models

import androidx.annotation.Keep

/**
 * Created by Pawan Rajotiya on 05-08-2024.
 */
@Keep
data class OptionItem(
    val id: Int,
    val title: String,
    val subTitle: String,
    val icon: Int
)

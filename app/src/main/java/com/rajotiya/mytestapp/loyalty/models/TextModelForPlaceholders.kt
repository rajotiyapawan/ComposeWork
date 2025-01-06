package com.rajotiya.mytestapp.loyalty.models

import androidx.annotation.Keep

/**
 * Created by Pawan Rajotiya on 06-01-2025.
 */

@Keep
data class TextModel(
    val text: String,
    val color: String,
    val font: String,
    val weight: String,
    val size: String,
    val placeholder: List<Placeholder>
)

@Keep
data class Placeholder(
    val text: String,
    val color: String,
    val font: String,
    val weight: String,
    val size: String
)

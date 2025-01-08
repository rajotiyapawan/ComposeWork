package com.rajotiya.mytestapp.loyalty.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

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
    val lineHeight: String?=null,
    val imgUrl: String?=null,
    @SerializedName("itemtype") val itemType: String?=null,
    val placeholder: List<Placeholder>?=null
)

@Keep
data class Placeholder(
    val text: String,
    val color: String,
    val font: String,
    val weight: String,
    val size: String
)

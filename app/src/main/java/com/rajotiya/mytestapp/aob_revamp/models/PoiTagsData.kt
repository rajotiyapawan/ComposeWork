package com.rajotiya.mytestapp.aob_revamp.models

import androidx.annotation.Keep

@Keep
data class PoiTagsData(
    val status: String?,
    val list: ArrayList<PoiTag>?
) {
    @Keep
    data class PoiTag(
        val id: String?=null,
        val value: String?=null,
        val url: String?=null
    )
}
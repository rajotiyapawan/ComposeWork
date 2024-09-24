package com.rajotiya.mytestapp.aob_revamp.models

import androidx.annotation.Keep


@Keep
data class PopularCitiesData(
    val status: String? = null,
    val popular: ArrayList<CityDetail>? = null,
    val all: ArrayList<CityDetail>? = null
) {
    @Keep
    data class CityDetail(
        val id: String?=null,
        val name: String?=null,
        val imgUrl: String?=null
    )
}
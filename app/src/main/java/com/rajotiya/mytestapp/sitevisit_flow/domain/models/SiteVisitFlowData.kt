package com.rajotiya.mytestapp.sitevisit_flow.domain.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SiteVisitFlowData(
    val date: String?=null,
    val time: String?=null,
    val projects: List<SvProjectItem>?=null,
    val pickUpLocation: String?=null
){
    @Keep
    data class SvProjectItem(
        @SerializedName("psmname")val prjName: String?,
        @SerializedName("ctname")val prjCity: String?,
        @SerializedName("prc", alternate = ["price"])val price: String?,
        val propType: String?,
        val area: String?,
        val possStatusD: String?,
        val bd: String?=null,
        val cg: String?=null,
        val psmid: String?=null,
        val lt: String?=null,
        val dist: String?=null,
        val pid: String?=null,
        val imgUrl: String?=null,
        val ct: String?=null,
        var isSelected: Boolean=false,
        @SerializedName("ltname")val ltName: String?=null,
    )
}
package com.rajotiya.mytestapp.sitevisit_flow.domain.models

import androidx.annotation.Keep

@Keep
data class SiteVisitFlowData(
    val date: String?=null,
    val time: String?=null,
    val projects: List<SvProjectItem>?=null,
    val pickUpLocation: String?=null
){
    @Keep
    data class SvProjectItem(
        val prjName:String,
        val prjCity: String,
        val price: String,
        val propType: String,
        val area: String,
        val possessionBy: String
    )
}
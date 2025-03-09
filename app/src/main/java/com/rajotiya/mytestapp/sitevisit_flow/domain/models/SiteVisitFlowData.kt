package com.rajotiya.mytestapp.sitevisit_flow.domain.models

import androidx.annotation.Keep

@Keep
data class SiteVisitFlowData(
    val date: String,
    val time: String,
    val projects: List<SvProjectItem>,
    val pickUpLocation: String
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
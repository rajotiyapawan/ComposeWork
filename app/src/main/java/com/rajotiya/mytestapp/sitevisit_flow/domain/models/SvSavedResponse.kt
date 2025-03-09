package com.rajotiya.mytestapp.sitevisit_flow.domain.models

import androidx.annotation.Keep

@Keep
data class SvSavedResponse(
    val status:String,
    val message: String,
    val date: String,
    val time: String,
    val bookingDetails: SvBookingDetails,
    val cabDetails: SvCabDetails,
    val trackMsg: String
){
    @Keep
    data class SvBookingDetails(
        val projects: List<SiteVisitFlowData.SvProjectItem>,
        val date: String,
        val time: String,
        val pickUpLocation: String
    )

    @Keep
    data class SvCabDetails(
        val cabNumber: String,
        val cabModel: String,
        val driverName: String,
        val driverRating: String,
        val thingsToRemember: List<String>,
        val note: String
    )
}

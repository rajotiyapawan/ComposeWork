package com.rajotiya.mytestapp.sitevisit_flow.domain.models

import androidx.annotation.Keep

@Keep
data class ConfirmSvBookingModel(
    val status: String,
    val message: String,
    val date: String,
    val time: String,
    val projects: List<SiteVisitFlowData.SvProjectItem>,
    val pickUpLocation: String
)
package com.til.mb.aob_v2.utils

import com.rajotiya.mytestapp.aob_revamp.models.PopularLtPrjData

/**
 * Created by Pawan Rajotiya on 26-08-2024.
 */
sealed class AobUserEvents {
    data class InitialSelection (val optionSelected: Int): AobUserEvents()
    data class NavigateTo (val route: String, val saveToBackStack: Boolean = true, val currentScreen: String = ""): AobUserEvents()
    data class BackToPoiWithResult (val poiLocation: PopularLtPrjData.LtPrjDetail?): AobUserEvents()
    data class PopBackTo(val route: String): AobUserEvents()
    object BackBtnClicked: AobUserEvents()
    object OpenSrp: AobUserEvents()
    object CheckForTrueCaller: AobUserEvents()
    data class OpenAutoSuggest(val from: String): AobUserEvents()
    object DoNothing: AobUserEvents()
}
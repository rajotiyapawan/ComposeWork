package com.rajotiya.mytestapp.sitevisit_flow

sealed class SvFlowUserEvents {
    data class NavigateTo(
        val route: String,
        val saveToBackStack: Boolean = true,
        val currentScreen: String = ""
    ) : SvFlowUserEvents()

    data class PopBackTo(val route: String) : SvFlowUserEvents()
    data object BackBtnClicked : SvFlowUserEvents()
    data object SkipBtnClicked : SvFlowUserEvents()
    data object NextWeekSelected : SvFlowUserEvents()
    data object FinishFlow : SvFlowUserEvents()
    data object DoNothing : SvFlowUserEvents()
}
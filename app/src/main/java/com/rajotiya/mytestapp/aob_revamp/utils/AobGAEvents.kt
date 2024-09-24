package com.rajotiya.mytestapp.aob_revamp.utils

/**
 * Created by Pawan Rajotiya on 05-09-2024.
 */
sealed class AobGAEvents {
    data class ScreenLoad(val screenName: AOBScreens) : AobGAEvents()
    data class InitialOptionSelected(val option: Int) : AobGAEvents()
    data class BackClicked(val screenName: AOBScreens) : AobGAEvents()
    data class SkipClicked(val screenName: AOBScreens) : AobGAEvents()
    data class ResetClicked(val screenName: AOBScreens) : AobGAEvents()
    data class CitySelected(val screenName: AOBScreens, val label: String, val city: String) : AobGAEvents()
    data class AutoSuggestScreenNext(val label: String, val locality: String, val project: String) : AobGAEvents()
    data class PoiScreenNext(val locality: String, val tag: String) : AobGAEvents()
    object FilterScreenError : AobGAEvents()
    object FilterScreenSubmit : AobGAEvents()
    object FilterScreenLoads : AobGAEvents()
    object RequirementBtnClicked : AobGAEvents()
    object RequirementTrueCallerOpen : AobGAEvents()
    object RequirementInfoFormOpen : AobGAEvents()
    object RequirementInfoFormError : AobGAEvents()
    object RequirementInfoFormSubmit : AobGAEvents()
    object RequirementOtpFormOpen : AobGAEvents()
    data class RequirementOtpFormError(val error: String) : AobGAEvents()
    object RequirementOtpFormResend : AobGAEvents()
    object RequirementOtpFormSubmit : AobGAEvents()
    object RequirementSkip : AobGAEvents()
    object RequirementSaved : AobGAEvents() //After otp verified
    data class PropertyServicesGA(val products: String) : AobGAEvents() //After otp verified
    object DoNothing : AobGAEvents()
}
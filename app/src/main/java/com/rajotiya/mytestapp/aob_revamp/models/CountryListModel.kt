package com.rajotiya.mytestapp.aob_revamp.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CountryListModel(
    @SerializedName("CountryCodes")
    val countryCodes:List<Country>
){
    @Keep
    data class Country(
        val code:String?,
        val displayName:String?,
        val isdCodes:String?
    )
}
package com.rajotiya.mytestapp.prime_grid.old

import android.graphics.drawable.Drawable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class MbPrimeBenifits(
    @SerializedName("bannerId") var bannerId: String? = "",
    @SerializedName("heading") val heading: String? = "",
    @SerializedName("moreText") var moreText: String? = "",
    @SerializedName("buttonText") var buttonText: String? = "",
    @SerializedName("icon") val icon: String? = "",
    @SerializedName("image") val image: String? = "",
    @SerializedName("bgColor") val bg: String? = "",
    @SerializedName("couponCode") var couponCode: String? = "",
    @SerializedName("validity") var validity: String? = "",
    @SerializedName("howItWorks") var howItWorks: ArrayList<String>? = null,
    @SerializedName("primeGeekLabBenefits") var primeGeekLabBenefits: ArrayList<PrimeModel>? = null,
    @SerializedName("termsOfUse") var termsOfUse: ArrayList<String>? = null,
    @SerializedName("offersUrl") var offersUrl: String? = null,
    @SerializedName("moreTextFields") val moreTextFields: List<String> = listOf(),
    @SerializedName("vidUrl") var vidUrl: String? = null,
    @SerializedName("vid") var vid: String? = null,
    var drawableIcon: Drawable? = null,
    var nonPrimeLimit: String? = " ",
    var primeLimit: String? = " ",
    var isTxtRow: Boolean = false
) : Serializable

@Keep
class PrimeModel {
    @SerializedName("title") var title : String?=""
    @SerializedName("subtitle") val subtitle : String?=""
    @SerializedName("icon") val icon : String?=""
}
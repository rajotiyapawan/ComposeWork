package com.magicbricks.prime.model

import com.google.gson.annotations.SerializedName

data class PrimePackageResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("subHeading") val subHeading: String?,
    @SerializedName("packageDetails") val packageDetails: PrimePackageDetails?
)

data class PrimePackageDetails(
    @SerializedName("packageID") val packageID: Any?,
    @SerializedName("payableAmount") val payableAmount: String?,
    @SerializedName("subTotal") val subTotal: Any?,
    @SerializedName("discountPercentage") val discountPercentage: String?,
    @SerializedName("packageList") val packageList: List<PackageItem>?,
    @SerializedName("mbPrimeBenifits") val mbPrimeBenifits: ArrayList<MbPrimeBenifits>?
)

data class PackageItem(
    @SerializedName("packegeName") val packageName: String?,
    @SerializedName("packegeid") val packageID: Int,
    @SerializedName("oprice") val price: Any?,
    @SerializedName("bprice") val bprice: Any?,
    @SerializedName("percentDiscountOff") val percentDiscountOff: Int?,
    @SerializedName("isDefault") val isDefault: Boolean?
)

data class MbPrimeBenifits(
    @SerializedName("bannerId") val bannerId: String?,
    @SerializedName("heading") val heading: String?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("moreText") val moreText: String?
)

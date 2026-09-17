package com.magicbricks.prime.grid.presentation.contract

import com.magicbricks.prime.model.MbPrimeBenifits
import com.magicbricks.prime.model.PackageItem
import com.magicbricks.prime.model.PrimePackageResponse

data class PrimeGridState(
    val isLoading: Boolean = true,
    val primePackageResponse: PrimePackageResponse? = null,
    val selectedPackageItem: PackageItem? = null,
    val benefits: ArrayList<MbPrimeBenifits>? = null,
    val category: String = "buy",
    val isNriUser: Boolean = false,
    val isWhatsAppSubscribed: Boolean = false,
    val errorMessage: String? = null
)

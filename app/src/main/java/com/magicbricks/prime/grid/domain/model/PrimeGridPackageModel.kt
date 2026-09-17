package com.magicbricks.prime.grid.domain.model

import com.magicbricks.prime.model.MbPrimeBenifits
import com.magicbricks.prime.model.PackageItem
import com.magicbricks.prime.model.PrimePackageResponse

data class PrimeGridPackageModel(
    val packageResponse: PrimePackageResponse?,
    val selectedPackageItem: PackageItem?,
    val benefits: ArrayList<MbPrimeBenifits>?,
    val isNriUser: Boolean,
    val category: String
)

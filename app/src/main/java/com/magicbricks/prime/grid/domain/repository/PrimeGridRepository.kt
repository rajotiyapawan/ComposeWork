package com.magicbricks.prime.grid.domain.repository

import com.magicbricks.prime.model.PrimePackageResponse
import com.rajotiya.mytestapp.utility.MBCoreResultEvent

interface PrimeGridRepository {
    suspend fun getPrimePackage(
        category: String,
        paymentSource: String,
        pid: String,
        mid: String,
        propertyId: String?
    ): MBCoreResultEvent<PrimePackageResponse>

    suspend fun subscribeWhatsApp(isSubscribed: Boolean): MBCoreResultEvent<Boolean>
}

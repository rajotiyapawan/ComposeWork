package com.magicbricks.prime.grid.domain.usecase

import com.magicbricks.prime.grid.domain.repository.PrimeGridRepository
import com.magicbricks.prime.model.PrimePackageResponse
import com.rajotiya.mytestapp.utility.MBCoreResultEvent

class GetPrimeGridPackageUseCase(private val repository: PrimeGridRepository) {
    suspend operator fun invoke(
        category: String,
        paymentSource: String,
        pid: String,
        mid: String,
        propertyId: String?
    ): MBCoreResultEvent<PrimePackageResponse> {
        return repository.getPrimePackage(category, paymentSource, pid, mid, propertyId)
    }
}

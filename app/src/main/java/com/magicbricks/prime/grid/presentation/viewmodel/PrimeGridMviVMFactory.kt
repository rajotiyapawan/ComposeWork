package com.magicbricks.prime.grid.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.magicbricks.prime.grid.domain.usecase.GetPrimeGridPackageUseCase

class PrimeGridMviVMFactory(
    private val getPrimeGridPackageUseCase: GetPrimeGridPackageUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrimeGridMviVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PrimeGridMviVM(getPrimeGridPackageUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

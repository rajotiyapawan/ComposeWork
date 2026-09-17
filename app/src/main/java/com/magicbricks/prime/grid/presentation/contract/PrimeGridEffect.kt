package com.magicbricks.prime.grid.presentation.contract

import com.magicbricks.prime.model.PackageItem
import com.rajotiya.mytestapp.base.UiEffect

sealed interface PrimeGridEffect : UiEffect {
    object NavigateBack : PrimeGridEffect
    object ShowTransparencyDialog : PrimeGridEffect
    data class StartPayment(val packageItem: PackageItem) : PrimeGridEffect
    data class ShowToast(val message: String) : PrimeGridEffect
}

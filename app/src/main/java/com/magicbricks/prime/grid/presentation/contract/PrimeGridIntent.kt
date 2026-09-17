package com.magicbricks.prime.grid.presentation.contract

import com.magicbricks.prime.model.PackageItem
import com.rajotiya.mytestapp.base.UiIntent

sealed interface PrimeGridIntent : UiIntent {
    object FetchInitialData : PrimeGridIntent
    object Refresh : PrimeGridIntent
    data class SelectPackage(val item: PackageItem) : PrimeGridIntent
    data class CategoryChanged(val category: String) : PrimeGridIntent
    object ShowTransparencyPromise : PrimeGridIntent
    data class ToggleWhatsAppSubscription(val isSubscribed: Boolean) : PrimeGridIntent
    data class ProceedToPayment(val packageItem: PackageItem) : PrimeGridIntent
    object BackClicked : PrimeGridIntent
}

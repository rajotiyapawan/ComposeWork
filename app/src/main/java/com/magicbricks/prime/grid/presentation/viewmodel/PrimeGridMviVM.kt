package com.magicbricks.prime.grid.presentation.viewmodel

import com.magicbricks.prime.grid.domain.usecase.GetPrimeGridPackageUseCase
import com.magicbricks.prime.grid.presentation.contract.PrimeGridEffect
import com.magicbricks.prime.grid.presentation.contract.PrimeGridIntent
import com.magicbricks.prime.grid.presentation.contract.PrimeGridState
import com.rajotiya.mytestapp.base.BaseViewModelMVI
import com.rajotiya.mytestapp.utility.MBCoreResultEvent

class PrimeGridMviVM(
    private val getPrimeGridPackageUseCase: GetPrimeGridPackageUseCase
) : BaseViewModelMVI<PrimeGridState, PrimeGridIntent, PrimeGridEffect>(PrimeGridState()) {

    override suspend fun handleIntent(intent: PrimeGridIntent) {
        when (intent) {
            is PrimeGridIntent.FetchInitialData -> loadPrimePackages()
            is PrimeGridIntent.Refresh -> loadPrimePackages()
            is PrimeGridIntent.SelectPackage -> {
                setState { copy(selectedPackageItem = intent.item) }
            }
            is PrimeGridIntent.CategoryChanged -> {
                setState { copy(category = intent.category) }
                loadPrimePackages()
            }
            is PrimeGridIntent.ShowTransparencyPromise -> {
                sendEffect { PrimeGridEffect.ShowTransparencyDialog }
            }
            is PrimeGridIntent.ToggleWhatsAppSubscription -> {
                setState { copy(isWhatsAppSubscribed = intent.isSubscribed) }
            }
            is PrimeGridIntent.ProceedToPayment -> {
                sendEffect { PrimeGridEffect.StartPayment(intent.packageItem) }
            }
            is PrimeGridIntent.BackClicked -> {
                sendEffect { PrimeGridEffect.NavigateBack }
            }
        }
    }

    private fun loadPrimePackages() {
        setState { copy(isLoading = true, errorMessage = null) }
        launchIO {
            val result = getPrimeGridPackageUseCase(
                category = uiState.value.category,
                paymentSource = "PrimeGridScreen",
                pid = "",
                mid = "",
                propertyId = null
            )
            when (result) {
                is MBCoreResultEvent.OnSuccess -> {
                    val response = result.data
                    val defaultPackage = response?.packageDetails?.packageList?.firstOrNull()
                    setState {
                        copy(
                            isLoading = false,
                            primePackageResponse = response,
                            selectedPackageItem = defaultPackage,
                            benefits = null
                        )
                    }
                }
                is MBCoreResultEvent.OnFailure -> {
                    setState { copy(isLoading = false, errorMessage = result.msg) }
                    sendEffect { PrimeGridEffect.ShowToast(result.msg) }
                }
                else -> {
                    setState { copy(isLoading = false, errorMessage = "Unknown error") }
                }
            }
        }
    }
}

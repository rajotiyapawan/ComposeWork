package com.rajotiya.mytestapp.loyalty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.rajotiya.mytestapp.loyalty.models.LoyaltyLandingPageData
import com.rajotiya.mytestapp.loyalty.models.RewardDetailData
import com.rajotiya.mytestapp.loyalty.usecase.ExperianUseCase
import com.rajotiya.mytestapp.loyalty.usecase.ExperianUseCaseRepoImpl
import com.rajotiya.mytestapp.loyalty.usecase.LoyaltyRepoImpl
import com.rajotiya.mytestapp.loyalty.usecase.LoyaltyUseCase
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import com.rajotiya.mytestapp.utility.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoyaltyViewModel(
    private val experianUseCase: ExperianUseCase,
    private val loyaltyUseCase: LoyaltyUseCase
) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Get the dependency in your factory
                LoyaltyViewModel(
                    experianUseCase = ExperianUseCase(ExperianUseCaseRepoImpl()),
                    loyaltyUseCase = LoyaltyUseCase(LoyaltyRepoImpl())
                )
            }
        }
    }

    // handling of user and ga events
    private val _userEvents = MutableLiveData<LoyaltyUserEvents?>()
    val userEvents: LiveData<LoyaltyUserEvents?>
        get() = _userEvents
    private val _gaEvents = MutableLiveData<LoyaltyGaEvents?>()
    val gaEvents: LiveData<LoyaltyGaEvents?>
        get() = _gaEvents

    fun sendUserEvent(events: LoyaltyUserEvents) {
        _userEvents.value = events
    }

    fun sendGaEvent(events: LoyaltyGaEvents, withDelay: Boolean = false) {
        viewModelScope.launch {
            if (withDelay) {
                delay(300)
            }
            _gaEvents.value = events
        }
    }

    fun clearUserEvent() {
        viewModelScope.launch {
            delay(500)
            _userEvents.value = LoyaltyUserEvents.DoNothing
        }
    }

    fun clearGaEvent() {
        _gaEvents.value = LoyaltyGaEvents.DoNothing
    }

    private var _landingPageData: MutableStateFlow<SingleLiveEvent<MBCoreResultEvent<LoyaltyLandingPageData>>> =
        MutableStateFlow(SingleLiveEvent(MBCoreResultEvent.OnLoading))
    val landingPageData = _landingPageData.asStateFlow()

    init {
        getLandingPageData()
    }

    private fun getLandingPageData() {
        viewModelScope.launch {
            loyaltyUseCase.getLandingPageData().collect {
                _landingPageData.value = SingleLiveEvent(it)
            }
        }
    }

    private var _rewardDetails: MutableStateFlow<SingleLiveEvent<MBCoreResultEvent<RewardDetailData>>> =
        MutableStateFlow(SingleLiveEvent(MBCoreResultEvent.OnLoading))
    val rewardDetails = _rewardDetails.asStateFlow()

    fun getRewardDetails(id: String) {
        viewModelScope.launch {
            loyaltyUseCase.getRewardsDetail(params = LoyaltyUseCase.RewardDetailParams(rewardId = id)).collect{
                _rewardDetails.value = SingleLiveEvent(it)
            }
        }
    }
}
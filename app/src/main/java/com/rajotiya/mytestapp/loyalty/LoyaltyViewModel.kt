package com.rajotiya.mytestapp.loyalty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.rajotiya.mytestapp.loyalty.models.LoyaltyLandingPageData
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTaskDetail
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTransactionHistory
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTransactionItem
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTransactions
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTransactionsModel
import com.rajotiya.mytestapp.loyalty.models.Placeholder
import com.rajotiya.mytestapp.loyalty.models.RewardDetailData
import com.rajotiya.mytestapp.loyalty.models.StepWisePoint
import com.rajotiya.mytestapp.loyalty.models.TaskDetailSection
import com.rajotiya.mytestapp.loyalty.models.TaskDetailStepWisePoints
import com.rajotiya.mytestapp.loyalty.models.TextModel
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

    private var _landingPageSavedData: LoyaltyLandingPageData?=null
    val landingPageSavedDetailData get() = _landingPageSavedData

    fun saveLandingPageData(data: LoyaltyLandingPageData) {
        _landingPageSavedData = data}

    init {
        getLandingPageData()
    }

    fun getLandingPageData() {
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

    fun getDummyRewardDetailResponse(): LoyaltyTaskDetail{
        return LoyaltyTaskDetail(
            title = TextModel(
                text = "Why choose Site Visits through Magicbricks?",
                color = "#303030",
                weight = "SemiBold",
                font = "Montserrat",
                size = "16",
                placeholder = listOf(
                    Placeholder(text = "through Magicbricks?", color = "#303030", weight = "Regular", font = "Montserrat", size = "16")
                )
            ), imgUrl = "",
            benefits = TaskDetailSection(
                listtype = "bullet",list = listOf(
                    TextModel(
                        text = "Schedule at your convenience",
                        color = "#303030",
                        weight = "Regular",
                        font = "Montserrat",
                        size = "14",
                        itemType= "bullet"
                    ),
                    TextModel(
                        text = "Dedicated relationship manager",
                        color = "#303030",
                        weight = "Regular",
                        font = "Montserrat",
                        size = "14",
                        itemType= "bullet"
                    ),
                    TextModel(
                        text = "Visit with your family & friends",
                        color = "#303030",
                        weight = "Regular",
                        font = "Montserrat",
                        size = "14",
                        itemType= "bullet"
                    ),
                    TextModel(
                        text = "Visit with your family & friends",
                        color = "#303030",
                        weight = "Regular",
                        font = "Montserrat",
                        size = "14",
                        itemType= "bullet"
                    ),
                )
            ),
            steps = TaskDetailSection(
                title = TextModel(
                    text = "Just 3 Easy Steps to earn points",
                    color = "#303030",
                    weight = "SemiBold",
                    font = "Montserrat",
                    size = "16",
                    placeholder = listOf(
                        Placeholder(text = "to earn points", color = "#303030", weight = "Regular", font = "Montserrat", size = "16")
                    )),
                listtype = "bullet",list = listOf(
                    TextModel(
                        text = "Schedule at your convenience",
                        color = "#303030",
                        weight = "Regular",
                        font = "Montserrat",
                        size = "14"
                    ),
                    TextModel(
                        text = "Dedicated relationship manager",
                        color = "#303030",
                        weight = "Regular",
                        font = "Montserrat",
                        size = "14"
                    ),
                    TextModel(
                        text = "Visit with your family & friends",
                        color = "#303030",
                        weight = "Regular",
                        font = "Montserrat",
                        size = "14"
                    ),
                    TextModel(
                        text = "Visit with your family & friends",
                        color = "#303030",
                        weight = "Regular",
                        font = "Montserrat",
                        size = "14"
                    ),
                )
            ),
            pointprogress = TaskDetailStepWisePoints(
                title = TextModel(
                    text = "Points you earn for each site visit",
                    color = "#303030",
                    weight = "SemiBold",
                    font = "Montserrat",
                    size = "16",
                    placeholder = listOf(
                        Placeholder(text = "for each site visit", color = "#303030", weight = "Regular", font = "Montserrat", size = "16")
                    )),
                listOf(
                    StepWisePoint(
                        title = TextModel(
                            text = "1st Site Visit",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            placeholder = listOf(
                                Placeholder(text = "Site Visit", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )), earnedPoints = "1000"
                    ),
                    StepWisePoint(
                        title = TextModel(
                            text = "2nd Site Visit",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            placeholder = listOf(
                                Placeholder(text = "Site Visit", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )), earnedPoints = "1000"
                    ),
                    StepWisePoint(
                        title = TextModel(
                            text = "3st Site Visit",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            placeholder = listOf(
                                Placeholder(text = "Site Visit", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )), earnedPoints = "1000"
                    ),
                    StepWisePoint(
                        title = TextModel(
                            text = "4st Site Visit",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            placeholder = listOf(
                                Placeholder(text = "Site Visit", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )), tobeearned = "1000"
                    ),
                    StepWisePoint(
                        title = TextModel(
                            text = "5st Site Visit",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            placeholder = listOf(
                                Placeholder(text = "Site Visit", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )), tobeearned = "1000"
                    ),
                    StepWisePoint(
                        title = TextModel(
                            text = "6st Site Visit",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            placeholder = listOf(
                                Placeholder(text = "Site Visit", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )), tobeearned = "1000"
                    ),
                    StepWisePoint(
                        title = TextModel(
                            text = "7st Site Visit",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            placeholder = listOf(
                                Placeholder(text = "Site Visit", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )), tobeearned = "1000"
                    ),
                    StepWisePoint(
                        title = TextModel(
                            text = "8st Site Visit",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            placeholder = listOf(
                                Placeholder(text = "Site Visit", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )), tobeearned = "1000"
                    ),
                )
            )
        )
    }

    fun getDummyLedgerResponse(): LoyaltyTransactionsModel{
        return LoyaltyTransactionsModel(
            balance = "2500",
            totalearned = "12333",
            totalspends = "15000",
            txns = LoyaltyTransactionHistory(
                title = "Transaction History",
                list = listOf(
                    LoyaltyTransactions(
                        title = "December 2024",
                        list = listOf(
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            )
                        )
                    ),
                    LoyaltyTransactions(
                        title = "November 2024",
                        list = listOf(
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            )
                        )
                    ),
                    LoyaltyTransactions(
                        title = "October 2024",
                        list = listOf(
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            )
                        )
                    ),
                    LoyaltyTransactions(
                        title = "December 2024",
                        list = listOf(
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            )
                        )
                    ),
                    LoyaltyTransactions(
                        title = "December 2024",
                        list = listOf(
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            ),
                            LoyaltyTransactionItem(
                                title = "Earned for 4th Site Visit",
                                date = "28 Dec'24", txntype = "earned", points = "2000"
                            )
                        )
                    ),
                )
            )
        )
    }
}
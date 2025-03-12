package com.rajotiya.mytestapp.sitevisit_flow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.rajotiya.mytestapp.sitevisit_flow.domain.models.SiteVisitFlowData
import com.rajotiya.mytestapp.sitevisit_flow.domain.models.SvSavedResponse
import com.rajotiya.mytestapp.utility.ComposeUIState
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Pawan Rajotiya on 06-03-2025.
 */
class SiteVisitFlowViewModel: ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SiteVisitFlowViewModel()
            }
        }
    }

    // save and handle SvFlowData
    private val _svFlowData = MutableStateFlow(SiteVisitFlowData())
    val svFlowData = _svFlowData.asStateFlow()

    // handling of user and ga events
    private val _userEvents = MutableLiveData<SvFlowUserEvents?>()
    val userEvents: LiveData<SvFlowUserEvents?>
        get() = _userEvents
    fun sendUserEvent(events: SvFlowUserEvents) {
        _userEvents.value = events
    }

    fun clearUserEvent() {
        viewModelScope.launch {
            delay(500)
            _userEvents.value = SvFlowUserEvents.DoNothing
        }
    }

    fun getFreeCabIntroData(){

    }

    private var _svLocationSearch = MutableStateFlow<ComposeUIState<MBCoreResultEvent<List<String>>>>(ComposeUIState(isIdle = true))
    val svLocationSearch = _svLocationSearch.asStateFlow()

    fun getLocationSearchResults(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isEmpty() || query.length<3) {
                _svLocationSearch.value = ComposeUIState(isIdle = true)
            } else {
                _svLocationSearch.value = ComposeUIState(MBCoreResultEvent.OnLoading)
                delay(400)
                _svLocationSearch.value = ComposeUIState(MBCoreResultEvent.OnSuccess(listOf(
                    "Kailash Hospital, Sec 27, Noida", "Kailash Colony, Sec 127, Noida", "Kaily Town, Sec 27, Noida",
                    "Kailash Hospital, Sec 27, Noida", "Kailash Colony, Sec 127, Noida", "Kaily Town, Sec 27, Noida"
                )))
            }
        }
    }

    fun savePickUpLocation(location:String){
        _svFlowData.update { current ->
            current.copy(pickUpLocation = location)
        }
        _svLocationSearch.value = ComposeUIState(isIdle = true)
        addDummyProjects()
    }

    private fun addDummyProjects(){
        _svFlowData.update { current ->
            current.copy(projects = listOf(
                getDummyProjectItem(), getDummyProjectItem()
            ))
        }
    }

    private fun getDummyProjectItem(): SiteVisitFlowData.SvProjectItem {
        return SiteVisitFlowData.SvProjectItem(
            prjName = "Mb Project 1",
            prjCity = "Noida",
            price = "2.01Cr", propType = "2BHK", area = "1890 sqft",
            possessionBy = "Dec'25"
        )
    }

    private var _saveSvBooking = MutableStateFlow<ComposeUIState<MBCoreResultEvent<SvSavedResponse>>>(ComposeUIState(isIdle = true))
    val saveSvBooking = _saveSvBooking.asStateFlow()
    fun saveSvBooking(){
        viewModelScope.launch(Dispatchers.IO) {
            _saveSvBooking.value = ComposeUIState(MBCoreResultEvent.OnLoading)
            delay(500)
            _saveSvBooking.value = ComposeUIState(MBCoreResultEvent.OnSuccess(getSvSavedResponseDummy()))
        }
    }

    private fun getSvSavedResponseDummy(): SvSavedResponse{
        return SvSavedResponse(
            status = "1", message = "Booked Successfully",
            date = "9 Mar", time = "10:00 PM",
            bookingDetails = SvSavedResponse.SvBookingDetails(
                projects = listOf(
                    SiteVisitFlowData.SvProjectItem(
                        prjName = "Mb Project 1",
                        prjCity = "Noida",
                        price = "2.01Cr", propType = "2BHK", area = "1890 sqft",
                        possessionBy = "Dec'25"
                    ),
                    SiteVisitFlowData.SvProjectItem(
                        prjName = "Mb Project 1",
                        prjCity = "Noida",
                        price = "2.01Cr", propType = "2BHK", area = "1890 sqft",
                        possessionBy = "Dec'25"
                    )
                ),
                date = "9 Mar", time = "10:00 PM", pickUpLocation = "Kailash Hospital, Sec 27, Noida"
            ),
            cabDetails = SvSavedResponse.SvCabDetails(
                cabNumber = "HRXX XXXX12", cabModel = "White Etios Diesel",
                driverName = "Driver Singh", driverRating = "4.5",
                thingsToRemember = listOf("It's absolutely Free - no hidden charges!","You can visit multiple projects in one trip","Keep the cab & driver for your entire trip"),
                note = "We will call you 30 mins before pickup to confirm your exact location"
            ),
            trackMsg = "You can track & edit your site visit bookings only on the App!"
        )
    }

    var selectedTabIndex by mutableStateOf(0)
    var selectedTime by mutableStateOf<Int?>(null)
    var selectedDate by mutableStateOf<Int?>(null)

    var tabTitles = listOf("Morning", "Afternoon", "Evening")
    val timeSlots = listOf(
        listOf( // Morning Slots
            TimeSlot("9:00 AM", 1),
            TimeSlot("9:30 AM", 2),
            TimeSlot("10:00 AM", 3),
            TimeSlot("10:30 AM", 4),
            TimeSlot("11:00 AM", 5),
            TimeSlot("11:30 AM", 6),
        ),
        listOf( // Afternoon Slots
            TimeSlot("12:00 PM", 7),
            TimeSlot("12:30 PM", 8),
            TimeSlot("1:00 PM", 9),
            TimeSlot("1:30 PM", 10),
            TimeSlot("2:00 PM", 11),
        ),
        listOf( // Evening Slots
            TimeSlot("5:00 PM", 12),
            TimeSlot("5:30 PM", 13),
            TimeSlot("6:00 PM", 14),
            TimeSlot("6:30 PM", 15),
            TimeSlot("7:00 PM", 16),
        )
    )

    fun selectTab(index: Int) {
        selectedTabIndex = index
    }

    fun selectTime(time: Int) {
        selectedTime = time
    }

    fun selectDate(date: Int) {
        selectedDate = date
    }
}

data class TimeSlot(val time: String, val id: Int)
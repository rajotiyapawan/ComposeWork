package com.rajotiya.mytestapp.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.rajotiya.mytestapp.loyalty.LoyaltyUserEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Pawan Rajotiya on 31-01-2025.
 */
class NavigationViewModel:ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Get the dependency in your factory
                NavigationViewModel()
            }
        }
    }

    // handling of user and ga events and activity navigation
    private val _userEvents = MutableLiveData<LoyaltyUserEvents?>()
    val userEvents: LiveData<LoyaltyUserEvents?> get() = _userEvents
    private val _activityNavigation = MutableLiveData<String>()
    val activityNavigation: LiveData<String> get() = _activityNavigation

    fun sendUserEvent(events: LoyaltyUserEvents, withDelay: Boolean = false) {
        viewModelScope.launch {
            if (withDelay) {
                delay(300)
            }
            _userEvents.value = events
        }
    }

    private fun sendActivityNavigation(activity: String) {
        viewModelScope.launch {
            _activityNavigation.value = activity
        }
    }

    fun clearUserEvent() {
        viewModelScope.launch {
            delay(500)
            _userEvents.value = LoyaltyUserEvents.DoNothing
        }
    }

    fun clearActivityNavigation() {
        _activityNavigation.value = ""
    }
}
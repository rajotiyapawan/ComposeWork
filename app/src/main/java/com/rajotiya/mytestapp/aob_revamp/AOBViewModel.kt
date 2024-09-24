package com.rajotiya.mytestapp.aob_revamp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.models.CountryListModel
import com.rajotiya.mytestapp.aob_revamp.models.OptionItem
import com.rajotiya.mytestapp.aob_revamp.utils.AobGAEvents
import com.rajotiya.mytestapp.aob_revamp.utils.HomeInteriors
import com.rajotiya.mytestapp.aob_revamp.utils.HomeLoans
import com.rajotiya.mytestapp.aob_revamp.utils.PropWorth
import com.rajotiya.mytestapp.aob_revamp.utils.SearchProperty
import com.rajotiya.mytestapp.aob_revamp.utils.SellProperty
import com.til.mb.aob_v2.utils.AobUserEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Pawan Rajotiya on 05-08-2024.
 */
class AOBViewModel : ViewModel() {
    private var countryList: CountryListModel? = null

    private val _userEvents = MutableLiveData<AobUserEvents?>()
    val userEvents: LiveData<AobUserEvents?>
        get() = _userEvents

    private val _gaEvents = MutableLiveData<AobGAEvents?>()
    val gaEvents: LiveData<AobGAEvents?>
        get() = _gaEvents

    private var _aobOptions = MutableStateFlow(arrayListOf<OptionItem>())
    val aobOptions = _aobOptions.asStateFlow()

    init {
        _aobOptions.value = getOptionList()
    }

    private fun getOptionList(): ArrayList<OptionItem> {
        val list = arrayListOf<OptionItem>()
        list.add(
            OptionItem(
                id = SearchProperty,
                title = "Search Property",
                subTitle = "Buy, Rent a Property",
                icon = R.drawable.ic_prime_bitmap_chat
            )
        )
        list.add(
            OptionItem(
                id = SellProperty,
                title = "Sell Property",
                subTitle = "Sell or Rent Out your Property",
                icon = R.drawable.ic_prime_bitmap_chat
            )
        )
        list.add(
            OptionItem(
                id = PropWorth,
                title = "PropWorth",
                subTitle = "Property Valuation",
                icon = R.drawable.ic_prime_bitmap_chat
            )
        )
        list.add(
            OptionItem(
                id = HomeLoans,
                title = "Home Loans",
                subTitle = "34+ Partner Banks",
                icon = R.drawable.ic_prime_bitmap_chat
            )
        )
        list.add(
            OptionItem(
                id = HomeInteriors,
                title = "Home Interiors",
                subTitle = "300+  interior design brands",
                icon = R.drawable.ic_prime_bitmap_chat
            )
        )
        return list
    }

    fun sendUserEvent(events: AobUserEvents) {
        _userEvents.value = events
    }

    fun sendGaEvent(events: AobGAEvents, withDelay: Boolean = false) {
        viewModelScope.launch {
            if (withDelay) {
                delay(300)
            }
            _gaEvents.value = events
        }
    }
}
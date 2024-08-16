package com.rajotiya.mytestapp.aob_revamp

import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.models.CountryListModel
import com.rajotiya.mytestapp.aob_revamp.models.OptionItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Modifier
import java.nio.charset.StandardCharsets

/**
 * Created by Pawan Rajotiya on 05-08-2024.
 */
class AOBViewModel : ViewModel() {
    private var countryList: CountryListModel? = null

    fun getOptionList(): ArrayList<OptionItem> {
        val list = arrayListOf<OptionItem>()
        list.add(
            OptionItem(id = SearchProperty,
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
}
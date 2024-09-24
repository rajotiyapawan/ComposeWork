package com.rajotiya.mytestapp.aob_revamp.utils

/**
 * Created by Pawan Rajotiya on 24-09-2024.
 */

enum class AOBScreens {
    InitialSelection, CitySelection, BuyMoreProducts, VerifyOtpScreen, UserDetailsScreen, POIScreen, FilterScreen, AutoSuggestScreen, AutoSuggestOnlyLocalityScreen
}

enum class SearchType(val value: Int) {
    Property_Buy(1), Property_Rent(1), Projects(26), Agents(31), Locality(-29), ProjectContact(5),
    Property_Post(811), COMMERCIAL_BUY(100), COMMERCIAL_RENT(101), ADVERTISER(2), PG(11), InvestorShowCase(7)
}
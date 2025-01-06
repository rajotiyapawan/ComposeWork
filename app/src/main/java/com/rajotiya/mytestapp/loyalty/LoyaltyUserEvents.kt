package com.rajotiya.mytestapp.loyalty

/**
 * Created by Pawan Rajotiya on 03-01-2025.
 */
sealed class LoyaltyUserEvents {
    data class NavigateTo(
        val route: String,
        val saveToBackStack: Boolean = true,
        val currentScreen: String = "") : LoyaltyUserEvents()
    data class PopBackTo(val route: String) : LoyaltyUserEvents()
    data object BackBtnClicked : LoyaltyUserEvents()
    data object DoNothing : LoyaltyUserEvents()
}
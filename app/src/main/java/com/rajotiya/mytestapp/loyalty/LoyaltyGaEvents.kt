package com.rajotiya.mytestapp.loyalty

/**
 * Created by Pawan Rajotiya on 03-01-2025.
 */
sealed class LoyaltyGaEvents {
    data class ScreenLoad(val screenName: LoyaltyScreens) : LoyaltyGaEvents()
    data class BackClicked(val screenName: LoyaltyScreens) : LoyaltyGaEvents()
    data class SkipClicked(val screenName: LoyaltyScreens) : LoyaltyGaEvents()
    data class ResetClicked(val screenName: LoyaltyScreens) : LoyaltyGaEvents()
    data object DoNothing : LoyaltyGaEvents()
}
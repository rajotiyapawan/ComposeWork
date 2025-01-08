package com.rajotiya.mytestapp.loyalty.models

import androidx.annotation.Keep

/**
 * Created by Pawan Rajotiya on 08-01-2025.
 */
@Keep
data class LoyaltyTransactionsModel(
    val balance: String?,
    val totalspends: String?,
    val totalearned: String?,
    val txns: LoyaltyTransactionHistory?
)

@Keep
data class LoyaltyTransactionHistory(
    val title: String?,
    val list: List<LoyaltyTransactions>?
)

@Keep
data class LoyaltyTransactions(
    val title: String?,
    val list: List<LoyaltyTransactionItem>?
)

@Keep
data class LoyaltyTransactionItem(
    val title: String?,
    val date: String?,
    val imgUrl: String?=null,
    val type: String?=null,
    val txntype: String?,
    val points: String?
)

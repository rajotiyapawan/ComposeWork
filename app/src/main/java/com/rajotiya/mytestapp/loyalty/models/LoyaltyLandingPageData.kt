package com.rajotiya.mytestapp.loyalty.models

import androidx.annotation.Keep

/**
 * Created by Pawan Rajotiya on 03-01-2025.
 */

@Keep
data class LoyaltyLandingPageData(
    val status: String,
    val title: String?,
    val titleUrl: String?,
    val subtitle: String?,
    val pnts: String?,
    val pnturl: String? = null,
    val tabs: List<String>?,
    val rwdurl: String?,
    val bgurl: String?,
    val vidId: String?,
    val vidurl: String?,
    val rwdsProgress: List<MileStone>?,
    val rewards: Rewards?,
    val help: LoyaltyHelp?,
    val faq: LoyaltyFaq?
)

@Keep
data class MileStone(
    val imgUrl: String?,
    val pnts: String?,
    val pntsD: String?,
    val claimed: String?=null,
    val locked: String?=null,
)

@Keep
data class Rewards(
    val title: String?,
    val gfturl: String?,
    val items: List<RewardItem>?
)

@Keep
data class RewardItem(
    val imgUrl: String?,
    val title: String?,
    val worth: String?,
    val points: String?,
    val pnturl: String? = null,
    val locked: String? = null,
    val claimed: String? = null,
)

@Keep
data class LoyaltyHelp(
    val title: String?,
    val steps: List<LoyaltyStep>?
)

@Keep
data class LoyaltyStep(
    val title: String?,
    val subtitle: String?,
    val imgUrl: String?,
)

@Keep
data class LoyaltyFaq(
    val title: String?,
    val items: List<FaqItem>?
)
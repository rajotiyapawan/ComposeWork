package com.rajotiya.mytestapp.loyalty.models

data class RewardDetailData(
    val status:String?,
    val title:String?,
    val titleUrl:String?,
    val earnedPoints:String?,
    val worth:String?,
    val points:String?,
    val pnturl:String?,
    val items: List<LoyaltySections>?
)

data class LoyaltySections(
    val text:String?,
    val type:String?,
    val color:String?,
    val weight:String?,
    val font:String?,
    val size:String?,
    val bgcolor:String?,
    val subtext: List<TextModel>? = null,
    val items: List<TextModel>? = null,
    val qna: List<FaqItem>? = null
)

data class FaqItem(
    val que: TextModel?,
    val ans: TextModel?
)

package com.rajotiya.mytestapp.loyalty.models

import androidx.annotation.Keep

@Keep
data class RewardDetailData(
    val status: String?,
    val title: String?,
    val titleUrl: String?,
    val earnedPoints: String?,
    val worth: String?,
    val points: String?,
    val pnturl: String?,
    val insufficient: String?,
    val insufficientPntData: InsufficientData?,
    val tasks: LoyaltyTasks?,
    val items: List<LoyaltySections>?
)

@Keep
data class InsufficientData(
    val title: String?,
    val pointsToEarn: String?
)

@Keep
data class LoyaltyTasks(
    val title: String?,
    val items: List<LoyaltyTaskItem>?
)

@Keep
data class LoyaltyTaskItem(
    val title: TextModel?,
    val type: String?,
    val points: String?,
    val pnturl: String? = null,
    val imgUrl: String? = null,
    val cta: String?,
    val benefits: LoyaltyTaskBenefits?,
    val detail: LoyaltyTaskDetail? = null,
)

@Keep
data class LoyaltyTaskBenefits(
    val title: String?,
    val list: List<LoyaltyTaskBenefitItem>?
)

@Keep
data class LoyaltyTaskBenefitItem(
    val text: TextModel?,
    val imgUrl: String?,
    val type: String?,
)

@Keep
data class LoyaltyTaskDetail(
    val title: TextModel?,
    val imgUrl: String?,
    val benefits: TaskDetailSection?,
    val steps: TaskDetailSection?,
    val pointprogress: TaskDetailStepWisePoints?,
)

@Keep
data class TaskDetailStepWisePoints(
    val title: TextModel?,
    val list: List<StepWisePoint>?
)

@Keep
data class StepWisePoint(
    val title: TextModel?,
    val earnedPoints: String? = null,
    val tobeearned: String? = null,
)

@Keep
data class TaskDetailSection(
    val title: TextModel? = null,
    val listtype: String?,
    val list: List<TextModel>?
)

@Keep
data class LoyaltySections(
    val text: String?,
    val type: String?,
    val color: String?,
    val weight: String?,
    val font: String?,
    val size: String?,
    val bgcolor: String?,
    val subtext: List<TextModel>? = null,
    val items: List<TextModel>? = null,
    val qna: List<FaqItem>? = null
)

@Keep
data class FaqItem(
    val que: TextModel?,
    val ans: TextModel?
)

package com.rajotiya.mytestapp.loyalty.usecase

/**
 * Created by Pawan Rajotiya on 03-01-2025.
 */
class LoyaltyUseCase(private val repo: LoyaltyRepo) {
    suspend fun getLandingPageData() = repo.getLandingPageData()
    suspend fun getRewardsDetail(params: RewardDetailParams) = repo.getRewardsDetail(params)

    data class RewardDetailParams(val rewardId:String)
}
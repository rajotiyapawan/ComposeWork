package com.rajotiya.mytestapp.loyalty.usecase

import com.rajotiya.mytestapp.loyalty.models.LoyaltyLandingPageData
import com.rajotiya.mytestapp.loyalty.models.RewardDetailData
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import kotlinx.coroutines.flow.Flow

/**
 * Created by Pawan Rajotiya on 03-01-2025.
 */
interface LoyaltyRepo {
    suspend fun getLandingPageData(): Flow<MBCoreResultEvent<LoyaltyLandingPageData>>
    suspend fun getRewardsDetail(params: LoyaltyUseCase.RewardDetailParams): Flow<MBCoreResultEvent<RewardDetailData>>
}
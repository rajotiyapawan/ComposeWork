package com.rajotiya.mytestapp.loyalty.usecase

import com.rajotiya.mytestapp.loyalty.models.LoyaltyLandingPageData
import com.rajotiya.mytestapp.loyalty.models.RewardDetailData
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Pawan Rajotiya on 03-01-2025.
 */
class LoyaltyRepoImpl : LoyaltyRepo {
    override suspend fun getLandingPageData(): Flow<MBCoreResultEvent<LoyaltyLandingPageData>> =
        flow {
            emit(MBCoreResultEvent.OnLoading)

            delay(500)

            emit(MBCoreResultEvent.OnSuccess(LoyaltyLandingPageData(status = "1")))
        }

    override suspend fun getRewardsDetail(params: LoyaltyUseCase.RewardDetailParams): Flow<MBCoreResultEvent<RewardDetailData>> =
        flow {
            emit(MBCoreResultEvent.OnLoading)

            delay(500)

            emit(MBCoreResultEvent.OnSuccess(RewardDetailData(status = "1")))
        }
}
package com.rajotiya.mytestapp.loyalty.usecase

import com.rajotiya.mytestapp.loyalty.models.ExperianData
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import kotlinx.coroutines.flow.Flow

interface ExperianUseCaseRepo {
    suspend fun getExperianData(params: ExperianUseCase.ExperianParams): Flow<MBCoreResultEvent<ExperianData>>
    suspend fun resendExperianOtp(params: ExperianUseCase.ExperianParams): Flow<MBCoreResultEvent<ExperianData>>
    suspend fun submitExperianOtp(params: ExperianUseCase.ExperianParams): Flow<MBCoreResultEvent<ExperianData>>
}
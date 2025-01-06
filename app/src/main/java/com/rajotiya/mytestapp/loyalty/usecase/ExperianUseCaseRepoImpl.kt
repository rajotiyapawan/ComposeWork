package com.rajotiya.mytestapp.loyalty.usecase

import com.rajotiya.mytestapp.loyalty.models.ExperianData
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExperianUseCaseRepoImpl : ExperianUseCaseRepo {
    override suspend fun getExperianData(params: ExperianUseCase.ExperianParams): Flow<MBCoreResultEvent<ExperianData>> = flow {
        emit(MBCoreResultEvent.OnLoading)
        delay(500)
        emit(MBCoreResultEvent.OnSuccess(ExperianData("", "", "", "", "", "")))
    }

    override suspend fun resendExperianOtp(params: ExperianUseCase.ExperianParams): Flow<MBCoreResultEvent<ExperianData>> = flow {
        emit(MBCoreResultEvent.OnLoading)
        delay(500)
        emit(MBCoreResultEvent.OnSuccess(ExperianData("", "", "", "", "", "")))

    }

    override suspend fun submitExperianOtp(params: ExperianUseCase.ExperianParams): Flow<MBCoreResultEvent<ExperianData>> = flow {
        emit(MBCoreResultEvent.OnLoading)
        delay(500)
        emit(MBCoreResultEvent.OnSuccess(ExperianData("", "", "", "", "", "")))
    }
}
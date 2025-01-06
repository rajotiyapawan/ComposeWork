package com.rajotiya.mytestapp.loyalty.usecase

class ExperianUseCase(private val repo: ExperianUseCaseRepo) {
    suspend fun getExperianData(params: ExperianParams) = repo.getExperianData(params)
    suspend fun resendExperianOtp(params: ExperianParams) = repo.resendExperianOtp(params)
    suspend fun submitExperianOtp(params: ExperianParams) = repo.submitExperianOtp(params)

    data class ExperianParams(
        val mobile: String,
        val email: String,
        val name: String,
        val otp: String?=null,
        val userId: String?=null,
        val mode: String?=null // encrypted mode (mb or experian)
    )
}
package com.rajotiya.mytestapp.loyalty.models

data class ExperianUserDto(
    val status: String,
    val experiandata: String // encrypted json for experian data
)

data class ExperianData(
    val status: String, // 1 - verified , 0 - unverified - not nullable
    val otpsent: String, // 2 - not required, 1 - sent , 0 - sent  - nullable
    val mbotpsent: String, // 2 - not required, 1 - sent , 0 - sent  - nullable
    val creditscore: String, // score of user
    val total: String, // total score
    val userId: String // encrypted userId //"creditscore" : "600",
)
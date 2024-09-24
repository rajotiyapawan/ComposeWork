package com.rajotiya.mytestapp.aob_revamp.models

import androidx.annotation.Keep

@Keep
data class PopularLtPrjData(
    val status: String?,
    val ct: String?,
    val ctnm: String?,
    val lt: String?,
    val ltnm: String?,
    val plocs: PopularItem?,
    val pprjs: PopularItem?,
) {
    @Keep
    data class PopularItem(
        val title: String?,
        val list: List<LtPrjDetail>?
    )

    @Keep
    data class LtPrjDetail(
        val id: String? = null,
        val name: String? = null,
        val type: String? = null,
        val avgPrc: String? = null,
        val lt: String? = null,
        val ltnm: String? = null,
        val ct: String? = null,
        val ctnm: String? = null,
        val imgUrl: String? = null
    )
}
package com.rajotiya.mytestapp.aob_revamp.models

import androidx.annotation.Keep

@Keep
data class SearchFilterFormDataState(
    val pTypeList: List<PropertySearchModelMapping> = arrayListOf(),
    val pTypeError: Int = 0,
    val bedroomList: List<DefaultSearchModelMapping> = arrayListOf(),
    val bedroomError: Int = 0,
    val pgType: DefaultSearchModelMapping = DefaultSearchModelMapping(),
    val pgTypeError: Int = 1,
    val budgetMax: DefaultSearchModelMapping = DefaultSearchModelMapping(),
    val budgetMin: DefaultSearchModelMapping = DefaultSearchModelMapping(),
    val budgetError: Int = 0,
    val possessionError: Int = 0,
    val possessionStatus: DefaultSearchModelMapping = DefaultSearchModelMapping(),
    val isResidential: Boolean = true,
    val showError: Boolean = false,
    val clearFilters: Boolean = false,
    val isPG: Boolean = false,
    val validate:Boolean = false
)
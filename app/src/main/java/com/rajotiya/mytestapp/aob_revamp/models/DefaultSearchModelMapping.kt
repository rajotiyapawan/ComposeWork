package com.rajotiya.mytestapp.aob_revamp.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Pawan Rajotiya on 24-09-2024.
 */
data class DefaultSearchModelMapping(
    @SerializedName("displayName")
    var displayName: String? = null,
    var displayValue: String? = null,
    @SerializedName("code")
    var code: String? = null,
    @SerializedName("order")
    var order: Int = 0,
    @SerializedName("id")
    var id: String? = null,
    var isChecked: Boolean = false,
    var isError: Boolean = false
) : Cloneable {

    // Secondary constructor
    constructor(displayName: String, code: String) : this() {
        this.displayName = displayName
        this.code = code
    }

    // Override clone method
    public override fun clone(): Any {
        return super.clone()
    }
}

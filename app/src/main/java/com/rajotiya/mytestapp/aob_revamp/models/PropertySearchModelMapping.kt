package com.rajotiya.mytestapp.aob_revamp.models

import androidx.annotation.Keep

/**
 * Created by Pawan Rajotiya on 24-09-2024.
 */

@Keep
data class PropertySearchModelMapping(
    var displayName: String? = null,
    var code: String? = null,
    var isChecked: Boolean = false,
    var type: String? = null,
    var isError: Boolean = false
) : Cloneable {

    public override fun clone(): Any {
        return super.clone()
    }
}

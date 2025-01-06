package com.rajotiya.mytestapp.utility

import androidx.annotation.Keep

/**
 * Created by Pawan Rajotiya on 06-01-2025.
 */
@Keep
sealed class MBCoreResultEvent<out R> {
    data class OnSuccess<out T>(val data:T):MBCoreResultEvent<T>()
    data class OnFailure( val msg:String,val data:Any? = null):MBCoreResultEvent<Nothing>()
    object OnLoading:MBCoreResultEvent<Nothing>()
}

val <T>MBCoreResultEvent<T>.value:T?
    get() = (this as? MBCoreResultEvent.OnSuccess)?.data
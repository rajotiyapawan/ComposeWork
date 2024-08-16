package com.rajotiya.mytestapp

import android.app.Application

object AppContext {
    private var context: Application? = null
    fun setApplicationContext(context: Application){
        AppContext.context = context
    }
    fun getApplicationContext() = context
}
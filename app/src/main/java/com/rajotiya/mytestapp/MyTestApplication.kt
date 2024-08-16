package com.rajotiya.mytestapp

import android.app.Application

/**
 * Created by Pawan Rajotiya on 08-08-2024.
 */
class MyTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppContext.setApplicationContext(this)
    }
}
package com.yy.adam.rootchecker

import android.app.Application
import android.content.Context

class MainApp : Application() {

    companion object {
        lateinit var globalContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        globalContext = this
    }
}
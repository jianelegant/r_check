package com.yy.adam.rootchecker

import android.app.Application
import android.content.Context
import com.flurry.android.FlurryAgent


class MainApp : Application() {

    companion object {
        lateinit var globalContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        globalContext = this

        initFlurry()
    }

    private fun initFlurry() {
        FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "RGXF9DSTSC399WJBB777")
    }
}
package com.yy.adam.rootchecker

import android.app.Application
import android.content.Context
import com.flurry.android.FlurryAgent
import com.google.android.gms.ads.MobileAds


class MainApp : Application() {

    companion object {
        lateinit var globalContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        globalContext = this

        initFlurry()
        initAd()
    }

    private fun initAd() {
        MobileAds.initialize(this, "ca-app-pub-5644941632262899~7314238573")
    }

    private fun initFlurry() {
        FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "RGXF9DSTSC399WJBB777")
    }
}
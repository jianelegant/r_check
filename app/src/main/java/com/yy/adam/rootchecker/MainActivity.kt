package com.yy.adam.rootchecker

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var mCheckRootText: TextView? = null
    var mModelText: TextView? = null
    var mOSText: TextView? = null
    var mBrandText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.id_toolbar))
        supportActionBar?.setTitle(R.string.app_name)

        initViews()
    }

    private fun initViews() {
        mCheckRootText = findViewById(R.id.id_check_root)
        mModelText = findViewById(R.id.id_model)
        mOSText = findViewById(R.id.id_os)
        mBrandText = findViewById(R.id.id_brand)

        mModelText?.setText("Model: " + android.os.Build.MODEL)
        mOSText?.setText("OS: Android " + android.os.Build.VERSION.RELEASE + "(SDK " +android.os.Build.VERSION.SDK_INT +")")
        mBrandText?.setText("Brand: " + android.os.Build.BRAND)

        mCheckRootText?.setOnClickListener{
            if(Util.checkDeviceRooted()) {
                onRooted()
            } else {
                onUnRooted()
            }
        }
    }

    private fun onUnRooted() {
        mCheckRootText?.setText("Not Rooted")
        mCheckRootText?.setTextColor(resources.getColor(R.color.red))
    }

    private fun onRooted() {
        mCheckRootText?.setText("Rooted")
        mCheckRootText?.setTextColor(resources.getColor(R.color.green))
    }
}

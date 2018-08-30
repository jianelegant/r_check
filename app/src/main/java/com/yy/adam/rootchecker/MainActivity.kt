package com.yy.adam.rootchecker

import android.graphics.Typeface
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var mCheckRootText: TextView? = null

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
        mCheckRootText?.setTypeface(mCheckRootText?.typeface, Typeface.BOLD)
    }
}

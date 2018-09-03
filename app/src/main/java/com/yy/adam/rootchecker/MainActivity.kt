package com.yy.adam.rootchecker

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {

    var mCheckRootText: TextView? = null
    var mModelText: TextView? = null
    var mOSText: TextView? = null
    var mBrandText: TextView? = null

    lateinit var mAdView : AdView

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_rate -> onRateClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onRateClicked() {
        var builder = AlertDialog.Builder(this)
                .setMessage(R.string.rate_msg)
                .setPositiveButton(R.string.goto_rate){_, _ ->
                    Util.goToRate(this@MainActivity)
                }.setNegativeButton(R.string.cancel, null)
        builder.create().show()
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
            onCheckRootClicked()
        }
        loadAd()
    }

    private fun onCheckRootClicked() {
        if(Util.isTestKeys()) {
            onTestkeys()
            return
        }
        if(Util.checkDeviceRooted()) {
            onRooted()
        } else {
            onUnRooted()
        }
    }

    private fun onTestkeys() {
        mCheckRootText?.setText(R.string.test_keys)
        mCheckRootText?.setTextColor(resources.getColor(R.color.orange))
        mCheckRootText?.setOnClickListener(null)
    }

    private fun onUnRooted() {
        mCheckRootText?.setText(R.string.not_rooted)
        mCheckRootText?.setTextColor(resources.getColor(R.color.red))
        mCheckRootText?.setOnClickListener(null)
    }

    private fun onRooted() {
        mCheckRootText?.setText(R.string.rooted)
        mCheckRootText?.setTextColor(resources.getColor(R.color.green))
        mCheckRootText?.setOnClickListener(null)
    }

    private fun loadAd() {
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}

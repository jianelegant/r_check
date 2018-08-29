package com.yy.adam.rootchecker

import android.text.TextUtils
import android.widget.Toast

class Util {

    companion object {
        fun toast(msg: String) {
            if(!TextUtils.isEmpty(msg)) {
                Toast.makeText(MainApp.globalContext, msg, Toast.LENGTH_SHORT).show()
            }
        }

        fun isDeviceRooted() : Boolean{
            return checkRootMethod1()
        }

        private fun checkRootMethod1() : Boolean{
            var isroot = false
            var buildTags = android.os.Build.TAGS
            if(!TextUtils.isEmpty(buildTags) && buildTags.contains("test-keys")) {
                isroot = true
            }
            return isroot
        }

        private fun checkRootMethod2() : Boolean{
            var isroot = false
            return isroot
        }

        private fun checkRootMethod3() : Boolean{
            var isroot = false
            return isroot
        }
    }
}
package com.yy.adam.rootchecker

import android.text.TextUtils
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class Util {

    companion object {
        fun toast(msg: String) {
            if(!TextUtils.isEmpty(msg)) {
                Toast.makeText(MainApp.globalContext, msg, Toast.LENGTH_SHORT).show()
            }
        }

        fun checkDeviceRooted() : Boolean{
            return checkRootMethod1() || checkRootMethod2() || checkRootMethod3()
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
            var paths = arrayOf("/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su")
            for(path in paths) {
                if(File(path).exists()) {
                    isroot = true
                    break
                }
            }
            return isroot
        }

        private fun checkRootMethod3() : Boolean{
            var isroot = false
            var proces : Process? = null
            try {
                proces = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
                var bufferedReader = BufferedReader(InputStreamReader(proces.inputStream))
                var line = bufferedReader.readLine()
                if (line != null) {
                    isroot = true
                }
            } catch (e:Exception) {
                e.printStackTrace()
            } finally {
                proces?.destroy()
            }
            return isroot
        }
    }
}
package com.yy.adam.rootchecker

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.widget.Toast
import java.io.*

class Util {

    companion object {
        fun toast(msg: String) {
            if(!TextUtils.isEmpty(msg)) {
                Toast.makeText(MainApp.globalContext, msg, Toast.LENGTH_SHORT).show()
            }
        }

        fun checkDeviceRooted() : Boolean{
            return checkRootMethod5()
        }

        fun isTestKeys() : Boolean{
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

        private fun checkRootMethod4() : Boolean {
            var isroot = false
            var proces : Process? = null
            try {
                proces = Runtime.getRuntime().exec("su")
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

        private fun checkRootMethod5() : Boolean {
            var isroot = false
            var suProcess: Process? = null
            var cmdStream: DataOutputStream? = null
            var bufferedReader: BufferedReader? = null

            try {
                suProcess = Runtime.getRuntime().exec("su")
                cmdStream = DataOutputStream(suProcess.outputStream)
                bufferedReader = BufferedReader(InputStreamReader(suProcess.inputStream))

                cmdStream.writeBytes("id\n")
                cmdStream.flush()

                var line = bufferedReader.readLine()
                var exitSu = false
                if (null == line) {
                    exitSu = false
                } else if (line.contains("uid=0")) {
                    isroot = true
                    exitSu = true
                } else {
                    exitSu = true
                }

                if (exitSu) {
                    cmdStream.writeBytes("exit\n")
                    cmdStream.flush()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cmdStream?.close()
                bufferedReader?.close()
                suProcess?.destroy()
            }
            return isroot
        }

        fun goToRate(activity: Activity) {
            var uri = Uri.parse("market://details?id=" + activity?.packageName)
            var deeplinkIntent = Intent(Intent.ACTION_VIEW, uri)
            deeplinkIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY
                    or Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
                    or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            try {
                activity?.startActivity(deeplinkIntent)
            } catch (e : ActivityNotFoundException) {
                activity?.startActivity(Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + activity.packageName)))
            }
        }
    }
}
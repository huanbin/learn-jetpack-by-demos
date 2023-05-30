package com.example.jetpack_demos.startup

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.jetpack_demos.R
import com.example.jetpack_demos.workmanager.MainActivity

const val REQUEST_CODE = 11000

class ShortcutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)
        val maxShortcutCountPerActivity =
            ShortcutManagerCompat.getMaxShortcutCountPerActivity(applicationContext)
        println("maxShortcutCountPerActivity=$maxShortcutCountPerActivity")
        println("ShortcutActivity onCreate")

        requestPermissions(arrayOf(Manifest.permission.INSTALL_SHORTCUT), 10001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            println("onActivityResult！")
        if (resultCode == PackageManager.PERMISSION_GRANTED && requestCode == 10001) {
            println("get INSTALL_SHORTCUT success！")
        }
    }

    fun createPinShortcut(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.`package` = "com.example.jetpack_demos"
        intent.setClass(applicationContext, MainActivity::class.java)
        val shortcutInfoCompat = ShortcutInfoCompat.Builder(applicationContext, "id2")
            .setIcon(IconCompat.createWithResource(applicationContext, R.mipmap.ic_launcher))
            .setShortLabel("pin快捷菜单")
            .setAlwaysBadged()
            .setIntent(intent)
            .build()
        val shortcutResultIntent =
            ShortcutManagerCompat.createShortcutResultIntent(applicationContext, shortcutInfoCompat)
        //一个发送广播的intent
        val broadcastPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            REQUEST_CODE,
            shortcutResultIntent,//可以是一个广播的intent
            PendingIntent.FLAG_IMMUTABLE
        )
        ShortcutManagerCompat.requestPinShortcut(
            applicationContext,
            shortcutInfoCompat,
            broadcastPendingIntent.intentSender
        )
    }

    fun createDynamicShortcut(view: View) {
        val shortcut = ShortcutInfoCompat.Builder(applicationContext, "id1")
            .setShortLabel("Website")
            .setLongLabel("Open the website")
            .setIcon(
                IconCompat.createWithResource(
                    applicationContext,
                    R.drawable.baseline_airplanemode_active_24
                )
            )
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.mysite.example.com/")
                ).apply {

                }
            )
            .build()

        ShortcutManagerCompat.pushDynamicShortcut(applicationContext, shortcut)
    }

    fun updateShortcut(view: View) {
        val shortcut = ShortcutInfoCompat.Builder(applicationContext, "id1")
            .setShortLabel("Website updated!!!")
            .setLongLabel("Open the website")
            .setIcon(
                IconCompat.createWithResource(
                    applicationContext,
                    R.drawable.baseline_airplanemode_active_24
                )
            )
            //必须设置，更新时页需要设置
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.mysite.example.com/")
                )
            )
            .build()
        //更新成功
//        ShortcutManagerCompat.updateShortcuts(applicationContext, listOf(shortcut))
        ShortcutManagerCompat.pushDynamicShortcut(applicationContext, shortcut)
    }

    fun deleteShortcut(view: View) {
//        ShortcutManagerCompat.removeDynamicShortcuts(applicationContext, listOf("id1"))
        //不同平台行为，API30及以上有不同行为（29及以下与removeDynamicShortcuts效果一样）
//        ShortcutManagerCompat.removeLongLivedShortcuts(applicationContext,"id1")
        //删除所有的动态快捷(long lived快捷可能仍旧可用),静态快捷无法删除
        ShortcutManagerCompat.removeAllDynamicShortcuts(applicationContext)
    }

    fun customShortcutActivity(view: View) {
        startActivity(Intent(this, CustomShortcutActivity::class.java))
    }
}
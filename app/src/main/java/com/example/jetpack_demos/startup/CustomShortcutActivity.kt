package com.example.jetpack_demos.startup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.jetpack_demos.R

/**
 *
 */
class CustomShortcutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_shortcut)
        println("CustomShortcutActivity onCreate intent.action=" + intent.action)
        val rateLimitingActive = ShortcutManagerCompat.isRateLimitingActive(applicationContext)
        println("rateLimitingActive=$rateLimitingActive")
        customShortcutActivity()
    }

    val id = "id400"
    fun customShortcutActivity() {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(applicationContext)) {
            println("isRequestPinShortcutSupported=true")

            val shortcuts = ShortcutManagerCompat.getShortcuts(
                applicationContext,
                ShortcutManagerCompat.FLAG_MATCH_PINNED
            )
            if (shortcuts.isEmpty()) {
                println("shortcut empty")
                createShortcutIntent()
            } else {
                shortcuts.forEach {
                    println("shortcut has already:${it.shortLabel}")
                    if (it.id == id) {

                    } else {

                    }
                }
            }
        } else {
            println("isRequestPinShortcutSupported=false")
        }
    }

    fun createShortcutIntent() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"))
        val label = "快捷菜单"
        val iconResource = R.drawable.ic_android_24dp
        val shortcutInfoCompat = ShortcutInfoCompat.Builder(applicationContext, id)
            .setIcon(IconCompat.createWithResource(applicationContext, iconResource))
            .setShortLabel(label)
            .setLongLabel("自定义快捷菜单")
            .setIntent(intent)
            .build()
        //createShortcutResultIntent内部已经兼容老版本了
        //google官方模拟器ok
        val createShortcutResultIntent =
            ShortcutManagerCompat.createShortcutResultIntent(
                applicationContext,
                shortcutInfoCompat
            )
        setResult(Activity.RESULT_OK, createShortcutResultIntent)
        finish()
    }
}
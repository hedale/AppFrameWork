package com.hele.mrd.app.lib.common.ext

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.gyf.immersionbar.ImmersionBar
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun AppCompatActivity.showDialogCompat(dialog: DialogFragment){
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(dialog, localClassName)
    transaction.commitAllowingStateLoss()
}


fun AppCompatActivity.startActivity(clz: Class<*>){
    startActivity(Intent(this,clz))
}


/**
 * 澄清式状态栏
 */
fun Activity.fullScreen() {
    ImmersionBar.with(this)
        .fullScreen(true)
        .init()
}

/**
 * 设置状态栏样式
 */
fun Activity.defaultStatusBar(
    statusBarColor: String = "#FFFFFFFF",
    navigationBarColor: String = "#FFFFFFFF",
    statusBarDarkFont: Boolean = true,
    navigationBarDarkIcon: Boolean = true,
    fitsSystemWindows: Boolean = false
) {
    ImmersionBar.with(this)
        .statusBarColor(if (statusBarColor.startsWith("#")) statusBarColor else "#$statusBarColor")
        .statusBarDarkFont(statusBarDarkFont)
        .fitsSystemWindows(fitsSystemWindows)
        .navigationBarColor(navigationBarColor)
        .navigationBarDarkIcon(navigationBarDarkIcon)
        .init()
}

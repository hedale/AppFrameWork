package com.hele.mrd.app.lib.base.ext

import android.content.Context
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.hele.mrd.app.lib.base.BaseDialog
import com.hele.mrd.app.lib.common.ext.showDialogCompat
import com.hele.mrd.app.lib.router.ActivityResultDto
import com.hele.mrd.app.lib.router.KEY_DATA
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun <T : Parcelable> AppCompatActivity.showDialogCompatAsync(dialog: BaseDialog<*, *>): T? = suspendCoroutine {
    if (dialog.isAdded) {
        it.resume(null)
        return@suspendCoroutine
    }
    dialog.setDismissCallback { intent ->
        val data = intent?.getParcelableExtra<ActivityResultDto<T>>(KEY_DATA)?.data
        it.resume(data)
    }
    showDialogCompat(dialog)
}

fun View.hideSoftInputWindow() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
}

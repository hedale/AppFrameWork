package com.hele.mrd.app.lib.base.ext

import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.hele.mrd.app.lib.base.BaseDialog
import com.hele.mrd.app.lib.common.ext.showDialogCompat
import com.hele.mrd.app.lib.router.ActivityResultDto
import com.hele.mrd.app.lib.router.KEY_DATA
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun <T : Parcelable> Fragment.showDialogCompatAsync(dialog: BaseDialog<*, *>): T? = suspendCoroutine {
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
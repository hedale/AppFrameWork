package com.hele.mrd.app.lib.router

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HRouter<T: Parcelable> {

    private var onResult: ((result: ActivityResultDto<T>?) -> Unit)? = null

    private var launcher: ActivityResultLauncher<Intent>? = null

    fun register(ctx: AppCompatActivity){
        launcher = ctx.registerForActivityResult(AnyResultContract<T>()){
            onResult?.invoke(it)
        }
    }

    suspend fun startActivityAsync(intent: Intent): ActivityResultDto<T>? = suspendCoroutine {
        launcher?.launch(intent)
        onResult = { result ->
            it.resume(result)
        }

    }


    class AnyResultContract<T: Parcelable> : ActivityResultContract<Intent, ActivityResultDto<T>?>() {

        override fun createIntent(context: Context, input: Intent): Intent {
            return input
        }

        override fun parseResult(resultCode: Int, intent: Intent?): ActivityResultDto<T>? {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                return intent?.getParcelableExtra(KEY_DATA)
            }
            return null
        }
    }

}
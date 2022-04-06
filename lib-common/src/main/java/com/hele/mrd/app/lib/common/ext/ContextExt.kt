package com.hele.mrd.app.lib.common.ext

import android.content.Context
import android.widget.Toast

fun Context.toast(content: String?){
    Toast.makeText(this,content ?: return,Toast.LENGTH_SHORT).show()
}


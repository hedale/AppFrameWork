package com.hele.mrd.app.lib.common.ext

import java.text.SimpleDateFormat
import java.util.*

fun String?.nullSafe(defaultValue: String = ""): String{
    return this ?: defaultValue
}

fun String?.parseDate(pattern: String = "yyyy-MM-dd HH:mm",default: Long = 0L): Long{
    if(this == null){
        return default
    }
    return SimpleDateFormat(pattern, Locale.CHINA).parse(this)?.time ?: default
}
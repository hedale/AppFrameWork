package com.hele.mrd.app.lib.base.ext

import com.hele.mrd.app.lib.base.BaseApp
import java.text.SimpleDateFormat
import java.util.*


private val displayMetrics by lazy {
    BaseApp.app.resources.displayMetrics
}



fun Int.toPx(): Float{
    return this.times(displayMetrics.density).plus(0.5f)
}

fun Int.isOdd(): Boolean{
    return this % 2 == 1
}

fun Long?.format(pattern: String = "yyyy-MM-dd HH:mm",default: String = ""): String{
    if(this == null){
        return default
    }
    return SimpleDateFormat(pattern, Locale.CHINA).format(Date(this))
}

fun Double?.nullSafe(defaultValue: Double = 0.0): Double{
    return this ?: defaultValue
}

fun Long?.nullSafe(defaultValue: Long = 0L): Long{
    return this ?: defaultValue
}

fun Int?.nullSafe(defaultValue: Int = 0): Int{
    return this ?: defaultValue
}
fun Float?.nullSafe(defaultValue: Float = 0f): Float{
    return this ?: defaultValue
}

fun Any.screenWidth(): Int{
    return displayMetrics.widthPixels
}
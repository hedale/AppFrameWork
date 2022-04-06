package com.hele.mrd.app.lib.common.ext

fun String?.nullSafe(defaultValue: String = ""): String{
    return this ?: defaultValue
}
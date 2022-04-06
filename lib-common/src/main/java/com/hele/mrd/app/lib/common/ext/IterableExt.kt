package com.hele.mrd.app.lib.common.ext

inline fun <T> Iterable<T>.forEachBreak(action: (T) -> Boolean) {
    for (element in this){
        val result = action(element)
        if(result){
            break
        }
    }
}
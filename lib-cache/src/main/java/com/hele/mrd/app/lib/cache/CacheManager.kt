package com.hele.mrd.app.lib.cache

import android.app.Application

class CacheManager<K, V>(private val maxSize: Int, val context: Application) {

    val appCache = LinkedHashMap<K, V>(100, 0.75f, true)

    val userCache = LinkedHashMap<K, V>(100, 0.75f, true)

    private var currentSize = 0

    private var userCacheCurrentSize = 0

    fun clearUserCache(){
        trimToSizeWithUser(0)
        SpUtil.getUserSp(context).edit().clear().apply()
    }

    fun clearAppCache(){
        trimToSize(0)
        SpUtil.getAppSp(context).edit().clear().apply()
    }

    fun clearAllCache(){
        clearAppCache()
        clearUserCache()
    }

    private fun getItemSize(value: V): Int {
        return 1
    }

    fun put(key: K, value: V, isUser: Boolean = false): V? {
        saveToSp(key, value, isUser)
        val result: V?
        if (isUser) {
            result = userCache.put(key, value)
            userCacheCurrentSize += getItemSize(value)
        } else {
            result = appCache.put(key, value)
            currentSize += getItemSize(value)
        }
        trimToSize(maxSize)
        trimToSizeWithUser(maxSize)
        return result
    }

    /**
     * 返回这个 `key` 在缓存中对应的 `value`, 如果返回 `null` 说明这个 `key` 没有对应的 `value`
     *
     * @param key 用来映射的 `key`
     * @return `value`
     */
    inline fun <reified T : V> get(key: K, isUser: Boolean = false): T? {
        val value = if (isUser) {
            userCache[key] as T?
        } else {
            appCache[key] as T?
        }
        return value ?: getFromSp<T>(key, isUser)
    }

    inline fun <reified T> getFromSp(key: K, isUser: Boolean): T? {
        if (key is String) {
            when (T::class) {
                Boolean::class -> {
                    return if (isUser) {
                        SpUtil.getUserBoolean(context, key) as T?
                    } else {
                        SpUtil.getAppBoolean(context, key) as T?
                    }
                }

                String::class -> {
                    return if (isUser) {
                        SpUtil.getUserString(context, key) as T?
                    } else {
                        SpUtil.getAppString(context, key) as T?
                    }
                }

                Int::class -> {
                    return if (isUser) {
                        SpUtil.getUserInt(context, key) as T?
                    } else {
                        SpUtil.getAppInt(context, key) as T?
                    }
                }

                Float::class -> {
                    return if (isUser) {
                        SpUtil.getUserFloat(context, key) as T?
                    } else {
                        SpUtil.getAppFloat(context, key) as T?
                    }
                }

                Long::class -> {
                    return if (isUser) {
                        SpUtil.getUserLong(context, key) as T?
                    } else {
                        SpUtil.getAppLong(context, key) as T?
                    }
                }


            }
        }
        return null
    }

    /**
     * 当指定的 size 小于当前缓存已占用的总 size 时,会开始清除缓存中最近最少使用的条目
     *
     * @param size `size`
     */
    @Synchronized
    private fun trimToSize(size: Int) {
        var last: Map.Entry<K, V>

        while (currentSize > size) {
            last = appCache.entries.iterator().next()
            val toRemove = last.value
            currentSize -= getItemSize(toRemove)
            val key = last.key
            appCache.remove(key)
        }
    }

    private fun trimToSizeWithUser(size: Int) {
        var last: Map.Entry<K, V>

        while (userCacheCurrentSize > size) {
            last = userCache.entries.iterator().next()
            val toRemove = last.value
            userCacheCurrentSize -= getItemSize(toRemove)
            val key = last.key
            userCache.remove(key)
        }
    }

    private fun saveToSp(key: K, value: V, isUser: Boolean) {
        if (key is String) {
            //只有key为String才保存到sp
            when (value) {
                is Boolean -> {
                    if (isUser) {
                        SpUtil.putUserBoolean(context, key, value)
                    } else {
                        SpUtil.putAppBoolean(context, key, value)
                    }
                }

                is String -> {
                    if (isUser) {
                        SpUtil.putUserString(context, key, value)
                    } else {
                        SpUtil.putAppString(context, key, value)
                    }
                }
                is Int -> {
                    if (isUser) {
                        SpUtil.putUserInt(context, key, value)
                    } else {
                        SpUtil.putAppInt(context, key, value)
                    }
                }
                is Float -> {
                    if (isUser) {
                        SpUtil.putUserFloat(context, key, value)
                    } else {
                        SpUtil.putAppFloat(context, key, value)
                    }
                }
                is Long -> {
                    if (isUser) {
                        SpUtil.putUserLong(context, key, value)
                    } else {
                        SpUtil.putAppLong(context, key, value)
                    }
                }


            }
        }
    }

}
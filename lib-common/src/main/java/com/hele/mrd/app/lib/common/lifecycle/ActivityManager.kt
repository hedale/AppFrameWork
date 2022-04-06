package com.hele.mrd.app.lib.common.lifecycle

import android.app.Activity
import java.util.*

object ActivityManager {

    private val activityList = LinkedList<Activity>()

    fun addActivity(activity: Activity) {
        activityList.add(activity)
    }

    fun remove(activity: Activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity)
        }
    }

    fun killAll() {
        val iterator = activityList.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            iterator.remove()
            next.finish()
        }
    }
}
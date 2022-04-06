package com.hele.mrd.app.lib.common.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.hele.mrd.app.lib.common.ext.defaultStatusBar
import com.hele.mrd.app.lib.common.immersion.ImmersionBar

class ActivityLifecycle: Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

        ActivityManager.addActivity(activity)

        if (activity.javaClass.isAnnotationPresent(ImmersionBar::class.java)) {
            val immersionBar = activity.javaClass.getAnnotation(ImmersionBar::class.java)
            if (immersionBar != null) {
                activity.defaultStatusBar(
                    statusBarColor = immersionBar.statusBarColor,
                    statusBarDarkFont = immersionBar.statusBarDarkFont,
                    fitsSystemWindows = immersionBar.fitsSystemWindows,
                    navigationBarColor = immersionBar.navigationBarColor
                )
            }
        }
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        ActivityManager.remove(activity)
    }
}
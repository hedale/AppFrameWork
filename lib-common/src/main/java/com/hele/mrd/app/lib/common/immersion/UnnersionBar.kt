package com.hele.mrd.app.lib.common.immersion

annotation class ImmersionBar(
    /** 状态栏颜色*/
    val statusBarColor: String = "#ffffff",
    /** 状态栏字体是否需要显示黑色*/
    val statusBarDarkFont: Boolean = false,
    /** paddingTop与status bar*/
    val fitsSystemWindows: Boolean = false,
    /** 底部状态栏颜色*/
    val navigationBarColor: String = "#ffffffff"
)

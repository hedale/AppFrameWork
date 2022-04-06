package com.hele.mrd.app.lib.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class ContentWidget : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var defaultView: View? = null

    var emptyView: View? = null

    var errorView: View? = null

    private lateinit var contentView: View

    override fun onFinishInflate() {
        super.onFinishInflate()
        init()
    }

    private fun init() {
        if (childCount == 1) {
            contentView = getChildAt(0)
        } else {
            throw IllegalArgumentException("只支持一个child")
        }
    }

    fun showEmptyView() {
        contentView.visibility = GONE

        if (emptyView == null) {
            emptyView = CommonEmptyView(context)
            val emptyView = CommonEmptyView(context)
            emptyView.setupContent("没有数据")
            this.emptyView = emptyView
        }

        if (emptyView?.parent == null) {
            addView(emptyView)
        }
        emptyView?.visibility = VISIBLE
        defaultView?.visibility = GONE
        errorView?.visibility = GONE
    }

    fun showContentView() {
        contentView.visibility = VISIBLE
        emptyView?.visibility = GONE
        defaultView?.visibility = GONE
        errorView?.visibility = GONE
    }

    fun showDefaultView() {
        contentView.visibility = GONE
        emptyView?.visibility = GONE
        if (defaultView == null) {
            defaultView = CommonDefaultView(context)
        }
        if (defaultView?.parent == null) {
            addView(defaultView)
        }
        defaultView?.visibility = VISIBLE
        errorView?.visibility = GONE
    }

    fun showErrorView() {
        contentView.visibility = GONE
        emptyView?.visibility = GONE
        defaultView?.visibility = GONE

        if (errorView == null) {
            val errorView = CommonErrorView(context)
            errorView.setupContent("网络请求失败")
            this.errorView = errorView
        }
        if (errorView?.parent == null) {
            addView(errorView)
        }
        errorView?.visibility = VISIBLE
    }

}
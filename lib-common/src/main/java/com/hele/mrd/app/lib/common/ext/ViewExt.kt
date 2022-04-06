package com.hele.mrd.app.lib.common.ext

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupGrid(spanCount: Int = 3, bloc: ((position: Int) -> Int)? = null) {
    setHasFixedSize(true)
    val layoutManager = GridLayoutManager(context, spanCount)
    bloc?.run {
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return bloc(position)
            }
        }
    }

    this.layoutManager = layoutManager
}

fun RecyclerView.setupVertical(orientation: Int = RecyclerView.VERTICAL) {
    setHasFixedSize(true)
    val layoutManager = LinearLayoutManager(context)
    layoutManager.orientation = orientation
    this.layoutManager = layoutManager
}
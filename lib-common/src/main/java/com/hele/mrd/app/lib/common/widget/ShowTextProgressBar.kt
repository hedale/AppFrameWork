package com.hele.mrd.app.lib.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ProgressBar

class ShowTextProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ProgressBar(context, attrs, defStyleAttr) {

    private var showText: String? = null

    private val paint by lazy {
        val p = Paint()

        p.isFakeBoldText = true
        p
    }
    private val rect = Rect()

    fun showText(showText: String,textSize: Float,textColor: Int = Color.WHITE) {
        this.showText = showText
        paint.textSize = textSize
        paint.color = textColor
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val text = showText ?: return
        this.paint.getTextBounds(text, 0, text.length, rect)
        val x: Int = width / 2 - rect.centerX()
        val y: Int = height / 2 - rect.centerY()
        canvas?.drawText(text, x.toFloat(), y.toFloat(), this.paint)
    }
}
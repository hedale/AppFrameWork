package com.hele.mrd.app.lib.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.hele.mrd.app.lib.common.databinding.LibCommonViewDefaultBinding

class CommonDefaultView: FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding = LibCommonViewDefaultBinding.inflate(LayoutInflater.from(context),this,true)

    fun setupContent(content: String){
        binding.tvContent.text = content
    }
}
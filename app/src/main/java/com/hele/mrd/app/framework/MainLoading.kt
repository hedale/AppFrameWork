package com.hele.mrd.app.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.hele.mrd.app.framework.databinding.DialogLoadingBinding
import com.hele.mrd.app.lib.base.ext.toPx
import com.hele.mrd.app.lib.base.view.ILoading

class MainLoading : ILoading<DialogLoadingBinding>() {

    companion object {

        private const val KEY_CONTENT = "key_content"

        fun newInstance(content: String?): ILoading<DialogLoadingBinding> {
            val bundle = Bundle()
            bundle.putString(KEY_CONTENT, content)
            val frag = MainLoading()
            frag.arguments = bundle
            return frag
        }
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): DialogLoadingBinding {
        return DialogLoadingBinding.inflate(inflater,container,false)
    }

    override fun tryLoadData() {
        viewBinding.tvContent.text = arguments?.getString(KEY_CONTENT)
    }

    override fun dialogWidth(): Int {
        return 150.toPx().toInt()
    }

    override fun dialogHeight(): Int {
        return 150.toPx().toInt()
    }

}
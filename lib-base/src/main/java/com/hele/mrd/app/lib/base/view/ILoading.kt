package com.hele.mrd.app.lib.base.view

import androidx.viewbinding.ViewBinding
import com.hele.mrd.app.lib.base.WithoutViewModelDialog

abstract class ILoading<VB : ViewBinding> : WithoutViewModelDialog<VB>() {


    override fun dialogAnimations(): Int {
        return 0
    }



}
package com.hele.mrd.app.lib.base

import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding

abstract class WithoutViewModelDialog<VB: ViewBinding>: BaseDialog<BaseViewModel<*>,VB>() {

    override fun createViewModel(): BaseViewModel<*> {
        return viewModels<BaseViewModel<*>>().value
    }

}
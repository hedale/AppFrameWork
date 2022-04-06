package com.hele.mrd.app.lib.base

import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding

abstract class WithoutViewModelFragment<VB: ViewBinding>: BaseFragment<BaseViewModel<*>,VB>() {

    override fun createViewModel(): BaseViewModel<*> {
        return viewModels<BaseViewModel<*>>().value
    }

}
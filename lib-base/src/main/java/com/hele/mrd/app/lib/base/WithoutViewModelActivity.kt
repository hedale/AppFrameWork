package com.hele.mrd.app.lib.base

import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding

abstract class WithoutViewModelActivity<VB: ViewBinding>: BaseActivity<BaseViewModel<*>,VB>() {

    override fun createViewModel(): BaseViewModel<*> {
        return viewModels<BaseViewModel<*>>().value
    }

}
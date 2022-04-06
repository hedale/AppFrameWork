package com.hele.mrd.app.lib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.hele.mrd.app.lib.base.view.ILoading
import com.hele.mrd.app.lib.common.ext.showDialogCompat
import com.hele.mrd.app.lib.common.ext.toast

abstract class BaseActivity<VM : BaseViewModel<*>, VB : ViewBinding> : AppCompatActivity() {

    /** mvvm - view model */
    protected lateinit var viewModel: VM

    /** 数据绑定 */
    protected lateinit var binding: VB

    private var dataLoaded = false

    private var loading: ILoading<out ViewBinding>? = null

    abstract fun createViewBinding(): VB

    abstract fun createViewModel(): VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createViewBinding()
        viewModel = createViewModel()
        setContentView(binding.root)
        lifecycle.addObserver(viewModel)
        initView()
        initObservables()
    }

    open fun initObservables() {
        viewModel.baseUI.loading.observe(this) {
            val app = viewModel.getApplication<BaseApp>()
            loading?.dismissAllowingStateLoss()
            if(it.first){
                loading = app.appComponent.createLoading(it.second)
                showDialogCompat(loading ?: return@observe)
            }

        }
        viewModel.baseUI.toast.observe(this) {
            val app = viewModel.getApplication<BaseApp>()
            app.toast(it)
        }

    }




    abstract fun initView()

    abstract fun tryLoadData()

    override fun onResume() {
        super.onResume()
        if(!dataLoaded) {
            tryLoadData()
            dataLoaded = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }


}
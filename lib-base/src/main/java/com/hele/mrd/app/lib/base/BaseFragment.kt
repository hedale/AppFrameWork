package com.hele.mrd.app.lib.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hele.mrd.app.lib.base.view.ILoading
import com.hele.mrd.app.lib.common.ext.showDialogCompat
import com.hele.mrd.app.lib.common.ext.toast

abstract class BaseFragment<VM: BaseViewModel<*>,VB: ViewBinding>: Fragment() {

    protected lateinit var viewModel: VM

    protected lateinit var binding: VB

    private var loading: ILoading<out ViewBinding>? = null

    private var dataLoaded = false

    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun createViewModel(): VM

    open fun tryLoadData(){}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = createViewModel()
        initView()
        initObservables()
    }

    abstract fun initView()

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


    override fun onResume() {
        super.onResume()
        if(!dataLoaded) {
            tryLoadData()
            dataLoaded = true
        }
    }


}
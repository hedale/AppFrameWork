package com.hele.mrd.app.lib.base

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.hele.mrd.app.lib.base.view.ILoading
import com.hele.mrd.app.lib.common.ext.showDialogCompat
import com.hele.mrd.app.lib.common.ext.toast

abstract class BaseDialog<VM: BaseViewModel<*>,VB: ViewBinding>: DialogFragment() {

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
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
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

    override fun onStart() {
        super.onStart()
        initDialog()
    }




    override fun onResume() {
        super.onResume()
        if(!dataLoaded) {
            tryLoadData()
            dataLoaded = true
        }
    }

    /**
     * 初始化对话框
     */
    open fun initDialog() {
        dialog?.window?.apply {
            // 去掉dialog默认的padding
            decorView.setPadding(0, 0, 0, 0)
            setBackgroundDrawable(ColorDrawable())
            attributes.width = dialogWidth()
            attributes.height = dialogHeight()
            attributes.gravity = dialogGravity()
            attributes = attributes
            setWindowAnimations(dialogAnimations())
        }
    }

    /**
     * 对话框位置
     */
    open fun dialogGravity(): Int {
        return Gravity.CENTER
    }

    /**
     * 对话框宽
     */
    open fun dialogWidth(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    /**
     * 对话框高
     */
    open fun dialogHeight(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }

    /**
     * 对话框动画
     */
    open fun dialogAnimations(): Int {
        return R.style.anim_bottom_to_bottom
    }

}
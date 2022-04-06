package com.hele.mrd.app.framework

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hele.mrd.app.framework.databinding.ActivityMainBinding
import com.hele.mrd.app.lib.base.BaseActivity
import com.hele.mrd.app.lib.base.BaseApp
import com.hele.mrd.app.lib.base.dataclass.BaseLiveDataDto
import com.hele.mrd.app.lib.common.widget.CommonErrorView

class MainActivity: BaseActivity<MainViewModel,ActivityMainBinding>() {

    private val errorView by lazy {
        CommonErrorView(this)
    }

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): MainViewModel {
        return viewModels<MainViewModel>().value
    }

    override fun initView() {
        binding.tvContent.text = "点击模拟操作"
        binding.widgetContent.showDefaultView()

        binding.widgetContent.errorView = errorView
        binding.tvContent.setOnClickListener {
            viewModel.fetchData(showLoading = true)
        }
    }

    override fun initObservables() {
        super.initObservables()
        viewModel.respLiveData.observe(this){
            if(it.data != null){
                binding.widgetContent.showContentView()
                binding.tvContent.text = it.data
            }else{
                errorView.setupContent(it.e?.message ?: "")
                binding.widgetContent.showErrorView()
            }
        }
    }

    override fun tryLoadData() {
        viewModel.fetchData()
    }



}
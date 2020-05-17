package com.hellodiffa.themealdb.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<B: ViewDataBinding, V: ViewModel> : AppCompatActivity(){

    private lateinit var mViewDataBinding : B
    private lateinit var mViewModel : V

    val binding : B get() = mViewDataBinding
    val viewModel : V get() = mViewModel

    abstract fun getViewModelClass() : Class<V>
    abstract fun initView()

    @LayoutRes
    abstract fun getLayoutResourceId() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        initView()
    }
}
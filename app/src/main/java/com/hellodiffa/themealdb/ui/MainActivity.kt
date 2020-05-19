package com.hellodiffa.themealdb.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.hellodiffa.themealdb.R
import com.hellodiffa.themealdb.base.BaseActivity
import com.hellodiffa.themealdb.common.ResultState
import com.hellodiffa.themealdb.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private lateinit var mAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = MainAdapter()
        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = mAdapter
        }

        val vm: MainViewModel = getViewModel<MainViewModel>()
        vm.categoriesListLiveData.observe(this, Observer {
            when (it.status) {
                ResultState.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (it.data != null)
                        mAdapter.dataSource = it.data
                }
                ResultState.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                ResultState.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        mAdapter.dataSource = it.data
                    }
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
